package survey.backend.dto;

import survey.backend.entities.UserRole;

public class UserRoleDto {
    private Long id;
    private String role;

    public UserRole toUserRole() {
        UserRole userRole = new UserRole();

        userRole.setId(this.id);
        userRole.setRole(this.role);

        return userRole;
    }
}
