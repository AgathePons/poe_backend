package survey.backend.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private Long id;
    private String text;
    private Long orderInQuestion;
}
