package survey.backend.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
  private int status;

  private String error;

  private List<AbstractErrorDetailMessage> details;
}
