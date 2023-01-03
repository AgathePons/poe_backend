package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.impl.PoeService;

import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

  static final String ITEM_TYPE = "Poe";

  @Autowired
  private PoeService poeService;

  @GetMapping
  //@PreAuthorize("hasRole('ADMIN')")
  public Iterable<PoeDto> getAll() {
    return this.poeService.findAll();
  }

  @GetMapping("{id}")
  //@PreAuthorize("hasRole('ADMIN')")
  public PoeDto getById(@PathVariable("id") long id) {
    Optional<PoeDto> optPoe = poeService.findById(id);
    if (optPoe.isPresent()) {
      return optPoe.get();
    } else {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  //@PreAuthorize("hasRole('ADMIN')")
  public PoeDto add(@RequestBody PoeDto poeDto) {
    return  poeService.add(poeDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  //@PreAuthorize("hasRole('ADMIN')")
  public void removeById(@PathVariable("id") long id) {
    if(!poeService.delete(id)) {
      throw NoDataFoundError.withId(ITEM_TYPE, id);
    }
  }

  @PutMapping
  //@PreAuthorize("hasRole('ADMIN')")
  public PoeDto update(@RequestBody PoeDto poeDto) {
    return poeService.update(poeDto)
            .orElseThrow(() -> NoDataFoundError.withId("Poe", Math.toIntExact(poeDto.getId())));
  }
}
