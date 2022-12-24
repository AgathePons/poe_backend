package survey.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserRequestDto {
    private String userLogin;
    private String userPassword;
    private List<String> roles;
}
