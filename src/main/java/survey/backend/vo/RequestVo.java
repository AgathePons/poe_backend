package survey.backend.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RequestVo {
    private String login;
    private String password;
    private List<String> userRoles;
}
