package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TraineeDto {
  private Integer id;
  private String lastName;
  private String firstName;
  private String email;
  private String phoneNumber;
  private LocalDate birthDate;
}
