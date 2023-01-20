package survey.backend.dto;


import lombok.*;
import survey.backend.entities.Answer;
import survey.backend.entities.Question;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class QuestionFullDto extends QuestionDto {

    private Set<Answer> answers = new HashSet<>();
    public List<Answer> getAnswers() {
        List<Answer> list = new ArrayList<>(this.answers);
        Collections.sort(list, new Comparator<Answer>() {
            @Override
            public int compare(Answer o1, Answer o2) {
                return o1.getOrderInQuestion().compareTo(o2.getOrderInQuestion());
            }
        });
        return list;
    }
    private Long order;
}
