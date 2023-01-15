package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
