package survey.backend.error;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class AbstractError extends RuntimeException {
  public AbstractError(String message) {
    super(message);
  }

  private Set<AbstractSubError> details = new HashSet<>();

}
