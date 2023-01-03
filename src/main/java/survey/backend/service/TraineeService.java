package survey.backend.service;

import survey.backend.dto.TraineeDto;

import java.util.Collection;
import java.util.Optional;

public interface TraineeService {
  /**
   * find all trainees
   * @return collection of trainees
   */
  Collection<TraineeDto> findAll();

  /**
   * find one traine by id
   * @param id
   * @return optional of trainee
   */
  Optional<TraineeDto> findById(long id);

  /**
   * search trainees with criteria lastname, firstname
   * one criterion can be null, not both
   * @param lastName
   * @param firstName
   * @return trainee set with this lastname (if not null) and this firstname (if not null)
   * empty set if no trainee found with these criteria or both criteria are null
   */
  Collection<TraineeDto> search(String lastName, String firstName);

  /**
   * add new trainee
   * @param traineeDto
   * @return trainee completed (id, default values)
   */
  TraineeDto add(TraineeDto traineeDto);

  /**
   * update trainee
   * @param traineeDto
   * @return trainee updated if found, else optional empty
   */
  Optional<TraineeDto> update(TraineeDto traineeDto);

  /**
   * delete trainee with its id
   * @param id
   * @return true if found and deleted, false if not found
   */
  boolean delete(long id);
}
