package survey.backend.components;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
  public static <T> Stream<T> toStream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }

  public static <T> Stream<T> toParallelStream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), true);
  }
}
