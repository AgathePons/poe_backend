package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.service.impl.PoeService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

  @Autowired
  private PoeService poeService;

  @GetMapping
  public Iterable<Poe> findAll() {
    return this.poeService.findAll();
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
