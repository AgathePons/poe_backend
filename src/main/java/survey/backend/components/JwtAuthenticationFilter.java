package survey.backend.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import survey.backend.error.jwt.JwtTokenMissingException;
import survey.backend.service.UserAuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            // TODO remove sout
            System.out.println("REQ >> " + request);
            System.out.println("HEADERS >>>>> " + request.getHeader("Authorization"));
            throw new JwtTokenMissingException("No JWT token found in the request headers");
        }

        String token = header.substring("Bearer".length() + 1);

        jwtUtil.validateToken(token);

        String userLogin = jwtUtil.getLogin(token);

        UserDetails userDetails = userAuthService.loadUserByUsername(userLogin);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);

    }
}
