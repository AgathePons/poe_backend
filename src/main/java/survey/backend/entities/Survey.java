package survey.backend.entities;

import survey.backend.enums.Level;
import survey.backend.enums.PoeType;

import javax.persistence.*;

@Entity
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
