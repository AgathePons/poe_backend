package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Poe;

public interface PoeRepository extends CrudRepository<Poe, Long> {
}
