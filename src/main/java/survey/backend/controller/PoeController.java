package survey.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

  @GetMapping
  public Set<PoeDto> list() {
    var poe1 = PoeDto.builder()
            .id(1)
            .title("Fullstack Java")
            .beginDate(LocalDate.of(2022, 11, 2))
            .endDate(LocalDate.of(2023, 1, 27))
            .build();
    var poe2 = PoeDto.builder()
            .id(2)
            .title("Macramé")
            .beginDate(LocalDate.of(2020, 10, 22))
            .endDate(LocalDate.of(2021, 2, 3))
            .build();
    var poe3 = PoeDto.builder()
            .id(3)
            .title("Taxidermie avancée")
            .beginDate(LocalDate.of(2023, 3, 4))
            .endDate(LocalDate.of(2023, 6, 17))
            .build();
    var poeSet = Set.of(poe1, poe2, poe3);
    return poeSet;
  }

  @GetMapping("{id}")
  public Optional<PoeDto> one(@PathVariable("id") int id) {
    return Optional.of(PoeDto.builder()
            .id(id)
            .title("Macramé")
            .beginDate(LocalDate.of(2022, 11, 2))
            .endDate(LocalDate.of(2023, 1, 27))
            .build()
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PoeDto add(@RequestBody PoeDto poeDto) {
    // TODO: add in under layer
    poeDto.setId(42);
    return  poeDto;
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeById(@PathVariable("id") int id) {
    // TODO remove
  }
}
