package survey.backend.service;

import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;

import java.util.Collection;
import java.util.Optional;

public interface SurveyService {
    /**
     * find all surveys
     * @return collection of surveys
     */
    Collection<SurveyDto> findAll();

    /**
     * find one survey by id
     *
     * @param id
     * @return optional of survey
     */
    Optional<SurveyFullDto> findById(long id);

    /**
     * add new survey
     * @param surveyDto
     * @return survey completed (id, default values)
     */
    SurveyDto add(SurveyDto surveyDto);

    /**
     * update survey
     * @param surveyDto
     * @return survey updated if found, else optional empty
     */
    Optional<SurveyDto> update(SurveyDto surveyDto);

    /**
     * delete survey with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    boolean delete(long id);
}
