package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {
  static final String ITEM_TYPE = "Trainee";
  @Autowired // DI (Dependency Injection)
  private TraineeService traineeService;

  /**
   * list of trainees
   * route /api/trainee/
   * @return
   */
  @GetMapping
  public Set<TraineeDto> getAll() {
    return  traineeService.findAll();
  }

  /**
   * a trainee by its id
   * route /api/trainee/{id}
   * @param id
   * @return the trainee
   */
  @GetMapping("{id}")
  public TraineeDto getById(@PathVariable("id") int id) {
    Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
    if (optTraineeDto.isPresent()) {
      return optTraineeDto.get();
    } else {
      throw NoDataFoundError.withId(ITEM_TYPE, id);

    }
  }

  /**
   * search trainee with criteria
   * route /api/trainee/search?fn=John&ln=Doe
   * @param firstName (optional)
   * @param lastName (optional)
   * @return trainee corresponding
   */
  @GetMapping("search")
  public Set<TraineeDto> search(
          @RequestParam(name="fn", required = false) String lastName,
          @RequestParam(name="ln", required = false) String firstName
  ) {
    return traineeService.search(lastName, firstName);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TraineeDto add(@Valid @RequestBody TraineeDto traineeDto) {
    // TODO traineeDto must be validate
    return traineeService.add(traineeDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean removeById(@PathVariable("id") int id) {
    boolean isExisting = traineeService.delete(id);
    if(isExisting) {
      return true;
    } else {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }

  @PutMapping("/{id}")
  public TraineeDto updatePartialById(@PathVariable("id") int id, @RequestBody TraineeDto updatedTrainee) {
    // TODO traineeDto must be validate
    Optional<TraineeDto> optTraineeDto = traineeService.update(updatedTrainee);
    if(optTraineeDto.isPresent()) {
      return updatedTrainee;
    } else {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }
}
