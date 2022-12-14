package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.TraineeDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.impl.PoeService;
import survey.backend.service.impl.TraineeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

  static final String ITEM_TYPE = "Poe";

  @Autowired
  private PoeService poeService;

  @Autowired
  private TraineeService traineeService;

  @GetMapping
  //@PreAuthorize("hasRole('ADMIN')")
  public Iterable<PoeDto> getAll() {
    return this.poeService.findAll();
  }

  @GetMapping("{id}")
  //@PreAuthorize("hasRole('ADMIN')")
  public PoeFullDto getById(@PathVariable("id") long id) {
    return poeService.findById(id)
            .orElseThrow(() -> NoDataFoundError.withId(ITEM_TYPE, id));
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

  @PatchMapping("{poeId}/add/{traineeId}")
//@PreAuthorize("hasRole('ADMIN')")
  public Optional<PoeFullDto> addOneTrainee(@PathVariable("poeId") long poeId , @PathVariable("traineeId") long traineeId) {
    // check if poe and trainee exist
    Optional<PoeFullDto> optPoe = poeService.findById(poeId);
    Optional<TraineeDto> optTrainee = traineeService.findById(traineeId);
    if (optPoe.isPresent()) {
      if (optTrainee.isPresent()) {
        System.out.println("===>>> POE: " + optPoe.get().getTitle());
        System.out.println("===>>> trainee: " + optTrainee.get().getFirstName() + ' ' + optTrainee.get().getLastName());
        // update the poe
        return poeService.addTrainee(poeId, traineeId);
      }
      // if trainee not found
      throw NoDataFoundError.withId("Trainee", traineeId);
    } else {
      // if poe not found
      throw NoDataFoundError.withId(ITEM_TYPE, poeId);
    }
  }

  @PatchMapping("{poeId}/remove/{traineeId}")
//@PreAuthorize("hasRole('ADMIN')")
  public Optional<PoeFullDto> removeOneTrainee(@PathVariable("poeId") long poeId , @PathVariable("traineeId") long traineeId) {
    // check if poe and trainee exist
    Optional<PoeFullDto> optPoe = poeService.findById(poeId);
    Optional<TraineeDto> optTrainee = traineeService.findById(traineeId);
    if (optPoe.isPresent()) {
      if (optTrainee.isPresent()) {
        System.out.println("===>>> POE: " + optPoe.get().getTitle());
        System.out.println("===>>> trainee: " + optTrainee.get().getFirstName() + ' ' + optTrainee.get().getLastName());
        // update the poe
        return poeService.removeTrainee(poeId, traineeId);
      }
      // if trainee not found
      throw NoDataFoundError.withId("Trainee", traineeId);
    } else {
      // if poe not found
      throw NoDataFoundError.withId(ITEM_TYPE, poeId);
    }
  }

  @PatchMapping("/{poeId}/addTrainees")
  public PoeFullDto addTrainees(
          @PathVariable("poeId") long poeId,
          @RequestBody List<Long> traineeIds
  ){
    return poeService.addTrainees(poeId, traineeIds)
            .orElseThrow(() -> {
              throw NoDataFoundError.withIds("Poe or trainees",
                      poeId);
            });
  }
}


