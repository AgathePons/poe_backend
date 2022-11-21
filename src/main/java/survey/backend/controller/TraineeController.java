package survey.backend.controller;

import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

  /**
   * list of trainees
   * route /api/trainee/
   * @return
   */
  @GetMapping
  public Set<TraineeDto> list() {
    var trainee1 = TraineeDto.builder()
            .id(1)
            .firstName("John")
            .lastName("Doe")
            .build();
    var trainee2 = TraineeDto.builder()
            .id(2)
            .firstName("Jane")
            .lastName("Doe")
            .build();
    var trainee3 = TraineeDto.builder()
            .id(3)
            .firstName("Micheline")
            .lastName("Duduche")
            .build();
    var traineeSet = Set.of(trainee1, trainee2, trainee3);
    return traineeSet;
  }

  /**
   * a trainee by its id
   * route /api/trainee/{id}
   * @param id
   * @return the trainee
   */
  @GetMapping("{id}")
  public Optional<TraineeDto> one(@PathVariable("id") int id) {
    // return  Optional.empty();
    return Optional.of(TraineeDto.builder()
            .id(id)
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1900, 7, 1))
            .build()
    );
  }

  /**
   * search trainee with criteria
   * route /api/trainee/search?fn=John&ln=Doe
   * @param firstName (optional)
   * @param lastName (optional)
   * @return trainee found
   */
  @GetMapping("search")
  public Set<TraineeDto> search(
          @RequestParam(name="fn", required = false) String firstName,
          @RequestParam(name="ln", required = false) String lastName
  ) {
    var trainee1 = TraineeDto.builder()
            .id(1)
            .firstName(Objects.isNull(firstName) ? "Found" : firstName)
            .lastName(Objects.isNull(lastName) ? "Funny" : lastName)
            .build();
    var trainee2 = TraineeDto.builder()
            .id(2)
            .firstName("Jim")
            .lastName("Found")
            .build();
    var trainee3 = TraineeDto.builder()
            .id(3)
            .firstName("James")
            .lastName("Found")
            .build();
    var traineeSet = Set.of(trainee1, trainee2, trainee3);
    return traineeSet;
  }

  @PostMapping
  public TraineeDto add(@RequestBody TraineeDto traineeDto) {
    // TODO: add in under layer
    traineeDto.setId(42);
    return traineeDto;
  }
}
