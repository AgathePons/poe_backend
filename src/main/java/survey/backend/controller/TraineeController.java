package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

  @Autowired // DI (Dependency Injection)
  private TraineeService traineeService;

  /**
   * list of trainees
   * route /api/trainee/
   * @return
   */
  @GetMapping
  public Set<TraineeDto> list() {
    return  traineeService.findAll();
//    var trainee1 = TraineeDto.builder()
//            .id(1)
//            .firstName("John")
//            .lastName("Doe")
//            .birthDate(LocalDate.of(1945, 5, 3))
//            .build();
//    var trainee2 = TraineeDto.builder()
//            .id(2)
//            .firstName("Jane")
//            .lastName("Doe")
//            .birthDate(LocalDate.of(1900, 12, 24))
//            .build();
//    var trainee3 = TraineeDto.builder()
//            .id(3)
//            .firstName("Micheline")
//            .lastName("Duduche")
//            .birthDate(LocalDate.of(2001, 11, 18))
//            .build();
//    var trainee4 = TraineeDto.builder()
//            .id(4)
//            .firstName("Jean-Michèle")
//            .lastName("Apeuprai")
//            .birthDate(LocalDate.of(1990, 6, 30))
//            .build();
//    var trainee5 = TraineeDto.builder()
//            .id(4)
//            .firstName("Philomène")
//            .lastName("Dupuy")
//            .birthDate(LocalDate.of(1650, 9, 1))
//            .build();
//    var traineeSet = Set.of(trainee1, trainee2, trainee3, trainee4, trainee5);
//    return traineeSet;
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
      throw NoDataFoundError.withId("Trainee", id);

    }
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
          @RequestParam(name="fn", required = false) String lastName,
          @RequestParam(name="ln", required = false) String firstName
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
  @ResponseStatus(HttpStatus.CREATED)
  public TraineeDto add(@RequestBody TraineeDto traineeDto) {
    return traineeService.add(traineeDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeById(@PathVariable("id") int id) {
    // TODO remove

  }

  @PutMapping("/{id}")
  public TraineeDto updatePartialById(@PathVariable("id") int id, @RequestBody TraineeDto updatedTrainee) {
    var trainee1 = TraineeDto.builder()
            .id(666)
            .firstName("John")
            .lastName("Doe")
            .email("johndoe@mail.com")
            .phoneNumber("06 55 88 99 66")
            .birthDate(LocalDate.of(1900, 7, 1))
            .build();
    trainee1.setFirstName(updatedTrainee.getFirstName());
    trainee1.setLastName(updatedTrainee.getLastName());
    trainee1.setEmail(updatedTrainee.getEmail());
    trainee1.setPhoneNumber(updatedTrainee.getPhoneNumber());
    trainee1.setBirthDate(updatedTrainee.getBirthDate());
    return  trainee1;
  }
}
