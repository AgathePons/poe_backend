package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PoeSurveyDto extends PoeFullDto {
    private Boolean status1;
    private Date sentDate1;
    private String link1;
    private Boolean status6;
    private Date sentDate6;
    private String link6;
    private Boolean status12;
    private Date sentDate12;
    private String link12;
}
