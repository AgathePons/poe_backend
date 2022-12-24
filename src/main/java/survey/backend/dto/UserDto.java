package survey.backend.dto;

import lombok.*;
import survey.backend.entities.User;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDto {
    private int id;
    private String login;
    private String password;
    private Set<UserRoleDto> userRoles;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setUserLogin(this.login);
        user.setUserPassword(this.password);
        // TODO see to fix type issue
        //user.setRoles(this.userRoles);

        return user;
    }
}
