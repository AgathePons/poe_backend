package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.Level;
import survey.backend.enums.PoeType;

import javax.persistence.*;

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
}
