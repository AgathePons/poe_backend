package survey.backend.error;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class NoDataFoundError extends RuntimeException  {

  public NoDataFoundError(String message) {
    super(message);
  }

  public static NoDataFoundError withId(String itemType, long id) {
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

  public static NoDataFoundError withIds(String itemTypes, long... ids) {
    return new NoDataFoundError(
            itemTypes
                    + " with ids "
                    + Arrays.stream(ids).mapToObj(id -> "" + id)
                    .collect(Collectors.joining(","))
                    + " not found");
  }

  public static NoDataFoundError withIds(String itemTypes, Collection<Long> ids) {
    return new NoDataFoundError(
            itemTypes
                    + " with ids "
                    + ids.stream().map(id -> "" + id)
                    .collect(Collectors.joining(","))
                    + " not found");
  }
}
