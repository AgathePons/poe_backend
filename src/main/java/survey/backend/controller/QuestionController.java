package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.QuestionDto;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.QuestionService;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    static final String ITEM_TYPE = "Question";

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public Iterable<QuestionDto> getAll() {
        return this.questionService.findAll();
    }

    @GetMapping("{id}")
    public QuestionDto getById(@PathVariable("id") long id) {
        return questionService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId(ITEM_TYPE, id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('ADMIN')")
    public QuestionDto add(@RequestBody QuestionDto questionDto) {
        return  questionService.add(questionDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('ADMIN')")
    public void removeById(@PathVariable("id") long id) {
        if(!questionService.delete(id)) {
            throw NoDataFoundError.withId(ITEM_TYPE, id);
        }
    }

    @PutMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public QuestionDto update(@RequestBody QuestionDto questionDto) {
        return questionService.update(questionDto)
                .orElseThrow(() -> NoDataFoundError.withId("Survey", Math.toIntExact(questionDto.getId())));
    }
}



