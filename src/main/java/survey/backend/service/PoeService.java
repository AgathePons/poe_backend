package survey.backend.service;

import survey.backend.dto.PoeDto;

import java.util.Collection;
import java.util.Optional;

public interface PoeService {
  /**
   * find all poes
   * @return collection of poes
   */
  Collection<PoeDto> findAll();

  /**
   * find one poe by id
   * @param id
   * @return optional of poe
   */
  Optional<PoeDto> findById(long id);

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

  /**
   * delete one poe by id
   * @param id
   * @return true if found and deleted, false if not found
   */
  boolean delete(long id);
}
