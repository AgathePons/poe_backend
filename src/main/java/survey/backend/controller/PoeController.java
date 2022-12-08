package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.impl.PoeService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

  static final String ITEM_TYPE = "Poe";

  @Autowired
  private PoeService poeService;

  @GetMapping
  public Iterable<Poe> findAll() {
    return this.poeService.findAll();
  }

  @GetMapping("{id}")
  public Poe getById(@PathVariable("id") int id) {
    Optional<Poe> oPoe = poeService.findById(id);
    if (oPoe.isPresent()) {
      return oPoe.get();
    } else {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Poe add(@RequestBody PoeDto poeDto) {

    return  poeService.add(poeDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeById(@PathVariable("id") int id) {
    // TODO remove
  }

  @PutMapping
  public Poe update(@RequestBody PoeDto poeDto) {
    return poeService.update(poeDto)
            .orElseThrow(() -> NoDataFoundError.withId("Poe", Math.toIntExact(poeDto.getId())));
  }
}
