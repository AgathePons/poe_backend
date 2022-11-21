package survey.backend.controller;

import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

  /**
   * list of trainees
   * route /api/trainee/
   * @return
   */
  @GetMapping
  public String list() {
    return "Here are all your trainees";
  }

  /**
   * a trainee by its id
   * route /api/trainee/{id}
   * @param id
   * @return the trainee
   */
  @GetMapping("{id}")
  public TraineeDto one(@PathVariable("id") int id) {
    return TraineeDto.builder()
            .id(id)
            .firstName("John")
            .lastName("Doe")
            .build();
  }

  /**
   * search trainee with criteria
   * route /api/trainee/search?fn=John&ln=Doe
   * @param firstName (optional)
   * @param lastName (optional)
   * @return trainee found
   */
  @GetMapping("search")
  public String search(
          @RequestParam(name="fn", required = false) String firstName,
          @RequestParam(name="ln", required = false) String lastName
  ) {
    return "search result: fn="
            + firstName
            +" AND ln="
            + lastName;
  }
}
