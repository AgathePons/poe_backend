package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    // JPQL/HQL
    @Query(
            value = "SELECT q FROM Question as q order by q.orderInSurvey asc"
    )
    List<Question> findAllByOrder();
}
