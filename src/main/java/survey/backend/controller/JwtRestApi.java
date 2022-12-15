package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// TODO import survey.backend.entities.User;
import survey.backend.error.DisabledUserException;
import survey.backend.service.impl.UserAuthServiceImpl;
import survey.backend.utils.CustomUserDetail;
import survey.backend.utils.JwtUtil;
import survey.backend.vo.RequestVo;
import survey.backend.vo.ResponseVo;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtRestApi {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/user/signin")
    public ResponseEntity<ResponseVo> generateJwtToken(@RequestBody RequestVo request) {
        // TODO remove sout
        System.out.println("REQUEST post /api/user/signin >> " + request);
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        }
        // TODO remove sout
        System.out.println("-------- >> authentification: " + authentication.getPrincipal().getClass());
        System.out.println("-------- >> authentification: " + authentication.getPrincipal());

        User user = (User) authentication.getPrincipal();

        Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());

        // TODO Set<String> roles = user.getUserRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet());

        String token = jwtUtil.generateToken(authentication);

        ResponseVo response = new ResponseVo();
        response.setToken(token);
        response.setRoles(roles.stream().collect(Collectors.toList()));

        return new ResponseEntity<ResponseVo>(response, HttpStatus.OK);
    }

    @PostMapping("/api/user/signup")
    public ResponseEntity<String> signup(@RequestBody RequestVo request) {
        System.out.println("REQUEST post /api/user/signup >> " + request);
        userAuthService.saveUser(request);

        return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
    }
}
