package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
}
