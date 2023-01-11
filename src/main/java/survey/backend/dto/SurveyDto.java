package survey.backend.dto;

import lombok.*;
import survey.backend.enums.PoeType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyDto {
    private Long id;
    private String title;
    private PoeType poeType;
}
