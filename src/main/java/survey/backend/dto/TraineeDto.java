package survey.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TraineeDto {

  private Long id;

  @NotBlank
  private String lastName;

  @NotBlank
  private String firstName;

  @NotNull
  @Email
  private String email;

  @Pattern(regexp = "^[\\+]?[(]?\\d{3}[)]?[-\\s\\.]?\\d{3}[-\\s\\.]?\\d{4,6}$")
  private String phoneNumber;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Past
  private Date birthDate;
}
