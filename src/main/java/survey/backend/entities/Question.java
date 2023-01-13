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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private AnswerType answerType;

    @ManyToOne // mode fetch LAZY by default
    @JoinColumn(name="answer_id")
    @Builder.Default
    private Answer answer;
}
