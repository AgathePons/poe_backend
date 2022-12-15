package survey.backend.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseVo {
    private String token;
    private List<String> roles;
}
