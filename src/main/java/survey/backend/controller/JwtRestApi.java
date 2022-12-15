package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.entities.User;
import survey.backend.service.impl.UserAuthServiceImpl;
import survey.backend.utils.JwtUtil;
import survey.backend.vo.RequestVo;

import java.util.Set;

@RestController
public class JwtRestApi {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<Response> generateJwtToken(@RequestBody RequestVo request) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch () {

        }

        User user = (User) authentication.getPrincipal();
        Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());

        String token = jwtUtil.generateToken(authentication);

        Response response = new Response();
        response.setToken(token);
        response.setRoles(roles.stream().collect(Collectors.toList()));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
