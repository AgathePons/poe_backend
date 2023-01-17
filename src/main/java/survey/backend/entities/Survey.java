package survey.backend.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.Level;
import survey.backend.enums.PoeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(length = 4)
    @Enumerated(EnumType.STRING)
    private PoeType poeType;

    @OneToMany // mode fetch LAZY by default
    @JoinColumn(name="survey_id")
    @Builder.Default
    private Set<Question> questions = new HashSet<>();


}
