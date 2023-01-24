package survey.backend.service;

import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.PoeStatusDto;
import survey.backend.dto.PoeSurveyDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PoeService {
  /**
   * find all poes
   * @return collection of poes
   */
  Collection<PoeDto> findAll();// TODO replace for a DTO without trainees with smart use of inheritance
  List<PoeSurveyDto> findAllWithSurvey();
  /**
   * find one poe by id
   * @param id
   * @return optional of poe
   */
  Optional<PoeFullDto> findById(long id);

  Optional<PoeSurveyDto> findByIdWithStatus(long id);
  /**
   * add one new poe
   * @param poeDto
   * @return poe completed (id, default values)
   */
  PoeDto add(PoeDto poeDto);

  /**
   * update one poe
   * @param poeDto
   * @return poe updated if found, else optional empty
   */
  Optional<PoeDto> update(PoeDto poeDto);

  Optional<PoeStatusDto> updateStatus(PoeStatusDto poeSurveyDto);
  /**
   * update one poe add one trainee
   * @param poeId
   * @param traineeId
   * @return poe updated if found, else optional empty
   */
  Optional<PoeFullDto> addTrainee(long poeId, long traineeId);

  /**
   * update one poe remove many trainees
   * @param poeId
   * @param traineeIds
   * @return poe updated if found, else optional empty
   */
  Optional<PoeFullDto> addTrainees(long poeId, Collection<Long> traineeIds);

  /**
   * update one poe remove trainee
   * @param poeId
   * @param traineeId
   * @return poe updated if found, else optional empty
   */
  Optional<PoeFullDto> removeTrainee(long poeId, long traineeId);

  /**
   * delete one poe by id
   * @param id
   * @return true if found and deleted, false if not found
   */
  boolean delete(long id);
}
