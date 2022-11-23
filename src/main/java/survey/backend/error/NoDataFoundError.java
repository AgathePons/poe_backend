package survey.backend.error;

public class NoDataFoundError extends AbstractError {
  public NoDataFoundError(String message) {
    super(message);
  }

  public static NoDataFoundError withId(String itemType, int id) {
      return new NoDataFoundError(itemType + " with id " + id + " not found");
    }
}
