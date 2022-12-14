package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
