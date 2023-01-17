package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.*;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.AnswerService;
import survey.backend.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    static final String ITEM_TYPE = "Question";
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public Iterable<QuestionDto> getAll() {
        return this.questionService.findAll();
    }

    @GetMapping("{id}")
    public QuestionFullDto getById(@PathVariable("id") long id) {
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

    @PatchMapping("{questionId}/add/{answerId}")
//@PreAuthorize("hasRole('ADMIN')")
    public Optional<QuestionFullDto> addOneAnswer(@PathVariable("questionId") long questionId , @PathVariable("answerId") long answerId) {
        // check if poe and trainee exist
        Optional<QuestionFullDto> optQuestion = questionService.findByIdFullDto(questionId);
        Optional<AnswerDto> optAnswer = answerService.findById(answerId);
        if (optQuestion.isPresent()) {
            if (optAnswer.isPresent()) {
                // update the poe
                return questionService.addAnswer(questionId, answerId);
            }
            // if trainee not found
            throw NoDataFoundError.withId("Answer", answerId);
        } else {
            // if poe not found
            throw NoDataFoundError.withId(ITEM_TYPE, questionId);
        }
    }

    @PatchMapping("/{questionId}/addAnswers")
    public QuestionFullDto addAnswers(
            @PathVariable("questionId") long questionId,
            @RequestBody List<Long> answerIds
    ){
        return questionService.addAnswers(questionId, answerIds)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withIds("Question or answers",
                            questionId);
                });
    }

    @PatchMapping("{questionId}/remove/{answerId}")
//@PreAuthorize("hasRole('ADMIN')")
    public Optional<QuestionFullDto> removeOneAnswer(@PathVariable("questionId") long questionId , @PathVariable("answerId") long answerId) {

        Optional<QuestionFullDto> optQuestion = questionService.findByIdFullDto(questionId);
        Optional<AnswerDto> optAnswer = answerService.findById(answerId);
        if (optQuestion.isPresent()) {
            if (optAnswer.isPresent()) {

                return questionService.removeAnswer(questionId, answerId);
            }
            // if trainee not found
            throw NoDataFoundError.withId("Answer", answerId);
        } else {
            // if poe not found
            throw NoDataFoundError.withId(ITEM_TYPE, questionId);
        }
    }
}



