package survey.backend.dto;


import lombok.*;
import survey.backend.entities.Answer;
import survey.backend.entities.Question;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class QuestionFullDto extends QuestionDto {

    private Set<Answer> answers;
    private Long order;
}
