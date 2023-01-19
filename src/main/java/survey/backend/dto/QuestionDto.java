package survey.backend.dto;

import lombok.*;
import survey.backend.enums.AnswerType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String text;
    private AnswerType answerType;
    private Long orderInSurvey;
}
