package survey.backend.error.jwt;

public class InvalidCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCredentialsException() {
        super("Credentials provided was invalid");
    }
}