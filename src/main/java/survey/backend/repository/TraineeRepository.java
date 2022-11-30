package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {

}
