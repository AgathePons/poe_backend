package survey.backend.service;

import survey.backend.dto.AnswerDto;

import java.util.Collection;
import java.util.Optional;

public interface AnswerService {
    /**
     * find all answers
     * @return collection of answers
     */
    Collection<AnswerDto> findAll();

    /**
     * find one answer by id
     * @param id
     * @return optional of answer
     */
    Optional<AnswerDto> findById(long id);

    /**
     * add new answer
     * @param answerDto
     * @return answer completed (id, default values)
     */
    AnswerDto add(AnswerDto answerDto);

    /**
     * update answer
     * @param answerDto
     * @return answer updated if found, else optional empty
     */
    Optional<AnswerDto> update(AnswerDto answerDto);

    /**
     * delete answer with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    boolean delete(long id);

    Optional<AnswerDto> addQuestion(long questionId, long answerId);
}
