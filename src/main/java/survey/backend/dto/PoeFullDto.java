package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.entities.Trainee;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
public class PoeFullDto extends PoeDto {
  private Set<Trainee> trainees;
}
