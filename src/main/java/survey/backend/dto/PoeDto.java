package survey.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import survey.backend.entities.Trainee;
import survey.backend.tools.PoeType;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeDto {
  private Long id;
  private String title;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date beginDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;
  private PoeType type;
  // TODO private Set<Trainee> trainees;
}
