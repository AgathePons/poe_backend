package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.AnswerType;

import javax.persistence.*;

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
}
