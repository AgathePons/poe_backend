package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.QuestionService;
import survey.backend.service.SurveyService;

@RestController
@RequestMapping("api/survey")
public class SurveyController {

    static final String ITEM_TYPE = "Survey";
    @Autowired
    private SurveyService surveyService ;

    @Autowired
    private QuestionService questionService ;

    @GetMapping
    public Iterable<SurveyDto> getAll() {
        return this.surveyService.findAll();
    }

    @GetMapping("{id}")
    public SurveyFullDto getById(@PathVariable("id") long id) {
        return surveyService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId(ITEM_TYPE, id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('ADMIN')")
    public SurveyDto add(@RequestBody SurveyDto surveyDto) {
        return  surveyService.add(surveyDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('ADMIN')")
    public void removeById(@PathVariable("id") long id) {
        if(!surveyService.delete(id)) {
            throw NoDataFoundError.withId(ITEM_TYPE, id);
        }
    }

    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public SurveyDto update(@RequestBody SurveyDto surveyDto) {
        return surveyService.update(surveyDto)
                .orElseThrow(() -> NoDataFoundError.withId("Survey", Math.toIntExact(surveyDto.getId())));
    }
}
