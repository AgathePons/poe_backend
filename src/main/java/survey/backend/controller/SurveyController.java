package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.*;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.QuestionService;
import survey.backend.service.SurveyService;

import java.util.Optional;

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

    @PutMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public SurveyDto update(@RequestBody SurveyDto surveyDto) {
        return surveyService.update(surveyDto)
                .orElseThrow(() -> NoDataFoundError.withId("Survey", Math.toIntExact(surveyDto.getId())));
    }

    @PatchMapping("{surveyId}/add/{questionId}")
//@PreAuthorize("hasRole('ADMIN')")
    public Optional<SurveyFullDto> addOneQuestion(@PathVariable("surveyId") long surveyId , @PathVariable("questionId") long questionId) {
        // check if poe and trainee exist
        Optional<SurveyFullDto> optSurvey = surveyService.findById(surveyId);
        Optional<QuestionDto> optQuestion = questionService.findById(questionId);
        if (optSurvey.isPresent()) {
            if (optQuestion.isPresent()) {
                System.out.println("===>>> Survey: " + optSurvey.get().getTitle());
                System.out.println("===>>> question: " + optQuestion.get().getText() + ' ' + optQuestion.get().getAnswerType());
                // update the poe
                return surveyService.addQuestion(surveyId, questionId);
            }
            // if trainee not found
            throw NoDataFoundError.withId("Question", questionId);
        } else {
            // if poe not found
            throw NoDataFoundError.withId(ITEM_TYPE, surveyId);
        }
    }
}
