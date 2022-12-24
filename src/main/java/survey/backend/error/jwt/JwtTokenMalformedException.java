package survey.backend.error.jwt;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    private static final long serialVersionUID = 1L;
    public JwtTokenMalformedException(String message) {
        super(message);
    }
}