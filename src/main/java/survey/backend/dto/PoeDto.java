package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeDto {
  private Integer id;
  private String title;
  private LocalDate beginDate;
  private LocalDate endDate;
  // private PoeType poeType;
}
