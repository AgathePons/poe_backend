package survey.backend.error;

public class BadRequestError extends RuntimeException {

  public BadRequestError(String message) {
    super(message);
  }

  public static BadRequestError withNoArgs(String itemType) {
    return new BadRequestError("Bad request: query for " + itemType + " must have ln for lastname and / or fn for firstname");
  }
}
