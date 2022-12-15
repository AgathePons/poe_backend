package survey.backend.dto;

import lombok.*;
import survey.backend.entities.User;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private Set<UserRoleDto> userRoles;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);
        // TODO see to fix type issue
        //user.setUserRoles(this.userRoles);

        return user;
    }
}
