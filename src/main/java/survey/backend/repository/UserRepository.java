package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
