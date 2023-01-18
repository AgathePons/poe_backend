package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Question;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SurveyFullDto extends SurveyDto{
    private Set<Question> questions = new HashSet<>();

    public List<Question> getQuestions() {
        List<Question> list = new ArrayList<>(this.questions);
        Collections.sort(list, new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                return o1.getOrderInSurvey().compareTo(o2.getOrderInSurvey());
            }
        });
        return list;
    }
}
