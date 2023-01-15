package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
