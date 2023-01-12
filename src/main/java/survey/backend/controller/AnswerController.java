package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.AnswerDto;
import survey.backend.dto.QuestionDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.AnswerService;
import survey.backend.service.QuestionService;

@RestController
@RequestMapping("api/answer")
public class AnswerController {


    static final String ITEM_TYPE = "Answer";

    @Autowired
    private AnswerService answerService;



    @GetMapping
    public Iterable<AnswerDto> getAll() {
        return this.answerService.findAll();
    }

    @GetMapping("{id}")
    public AnswerDto getById(@PathVariable("id") long id) {
        return answerService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId(ITEM_TYPE, id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('ADMIN')")
    public AnswerDto add(@RequestBody AnswerDto answerDto) {
        return  answerService.add(answerDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('ADMIN')")
    public void removeById(@PathVariable("id") long id) {
        if(!answerService.delete(id)) {
            throw NoDataFoundError.withId(ITEM_TYPE, id);
        }
    }

    @PutMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public AnswerDto update(@RequestBody AnswerDto answerDto) {
        return answerService.update(answerDto)
                .orElseThrow(() -> NoDataFoundError.withId("Survey", Math.toIntExact(answerDto.getId())));
    }
}
