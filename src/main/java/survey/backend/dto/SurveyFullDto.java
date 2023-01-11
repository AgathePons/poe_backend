package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Question;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SurveyFullDto extends SurveyDto{
    private Set<Question> questions = new HashSet<>();
}
