package survey.backend.service;

import survey.backend.dto.QuestionDto;
import survey.backend.dto.QuestionFullDto;

import java.util.Collection;
import java.util.Optional;

public interface QuestionService {
    /**
     * find all questions
     * @return collection of questions
     */
    Collection<QuestionDto> findAll();

    /**
     * find one question by id
     * @param id
     * @return optional of question
     */
    Optional<QuestionFullDto> findByIdFullDto(long id);
    Optional<QuestionFullDto> findById(long id);

    /**
     * add new question
     * @param questionDto
     * @return question completed (id, default values)
     */
    QuestionDto add(QuestionDto questionDto);

    /**
     * update question
     * @param questionDto
     * @return question updated if found, else optional empty
     */
    Optional<QuestionDto> update(QuestionDto questionDto);

    /**
     * delete question with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    boolean delete(long id);

    Optional<QuestionFullDto> addAnswer(long questionId, long answerId);

    Optional<QuestionFullDto> addAnswers(long questionId, Collection<Long> answerId);

    Optional<QuestionFullDto> removeAnswer(long questionId, long answerId);


}
