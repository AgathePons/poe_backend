package survey.backend.error.jwt;

public class DisabledUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DisabledUserException() {
        super("User was disabled");
    }
}
