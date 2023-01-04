package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Trainee;

import java.util.Set;

@Getter @Setter
public class PoeFullDto extends PoeDto {
  private Set<Trainee> trainees;
}
