package survey.backend.entities;

import survey.backend.enums.Level;
import survey.backend.enums.PoeType;

public class Survey {
    private Long id;
    private String title;
    private Level level;
    private PoeType poeType;
    private String answerType;
}
