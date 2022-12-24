package survey.backend.error.jwt;

public class DisabledUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DisabledUserException(String msg) {
        super(msg);
    }
}
