package survey.backend.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDto {

    private String subject;
    private String body;
}
