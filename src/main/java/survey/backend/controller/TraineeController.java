package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.error.BadRequestError;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {
  static final String ITEM_TYPE = "Trainee";
  @Autowired // DI (Dependency Injection): see singleton
  private TraineeService traineeService;

  /**
   * list of trainees
   * route /api/trainee/
   * @return
   */
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Iterable<Trainee> getAll() {
    return traineeService.findAll();
  }

  /**
   * a trainee by its id
   * route /api/trainee/{id}
   * @param id
   * @return the trainee
   */
  @GetMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Trainee getById(@PathVariable("id") int id) {
    Optional<Trainee> optTrainee = traineeService.findById(id);
    if (optTrainee.isPresent()) {
      return optTrainee.get();
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
  @PreAuthorize("hasRole('ADMIN')")
  public Iterable<Trainee> search(
          @RequestParam(name="ln", required = false) String lastName,
          @RequestParam(name="fn", required = false) String firstName
  ) {
    if (lastName == null && firstName == null) {
      throw BadRequestError.withNoArgs(ITEM_TYPE);
    }

    List<Trainee> trainees = StreamSupport.stream(
            traineeService.search(lastName, firstName).spliterator(), false
    ).toList();

    if (trainees.isEmpty()) {
      throw NoDataFoundError.noResult(ITEM_TYPE, lastName, firstName);
    }
    return traineeService.search(lastName, firstName);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ADMIN')")
  public Trainee add(@Valid @RequestBody TraineeDto traineeDto) {
    // TODO traineeDto must be validate
    return traineeService.add(traineeDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  public void removeById(@PathVariable("id") int id) {
    if(!traineeService.delete(id)) {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }

  @PutMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Trainee update(@Valid @RequestBody TraineeDto traineeDto) {
    return traineeService.update(traineeDto)
            .orElseThrow(() -> NoDataFoundError.withId("Trainee", Math.toIntExact(traineeDto.getId())));
  }
}
