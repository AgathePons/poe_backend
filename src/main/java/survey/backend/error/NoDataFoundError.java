package survey.backend.error;

public class NoDataFoundError extends RuntimeException  {

  public NoDataFoundError(String message) {
    super(message);
  }

  public static NoDataFoundError withId(String itemType, int id) {
    return new NoDataFoundError(itemType + " with id " + id + " not found");
  }

  public static NoDataFoundError noResult(String itemType, String firstAttribute, String secondAttribute) {
    return new NoDataFoundError("Search for "
            + itemType
            + " with attributes "
            + firstAttribute
            + " and "
            +secondAttribute
            + " returns no result");
  }
}
