package survey.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserResponseDto {
    private String token;
    private List<String> roles;
}
