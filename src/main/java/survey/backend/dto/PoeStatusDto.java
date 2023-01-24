package survey.backend.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PoeStatusDto {
    private Long id;
    private Boolean status1;
    private Date sentDate1;
    private Boolean status6;
    private Date sentDate6;
    private Boolean status12;
    private Date sentDate12;
}
