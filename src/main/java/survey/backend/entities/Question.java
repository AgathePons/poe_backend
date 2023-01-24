package survey.backend.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.AnswerType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long orderInSurvey = 0L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private AnswerType answerType;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true) // mode fetch LAZY by default
    @JoinColumn(name="question_id")
    @OrderBy("orderInQuestion ASC")
    @Builder.Default
    private Set<Answer> answers = new HashSet<>();


}
