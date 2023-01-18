package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import survey.backend.components.StreamUtils;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.QuestionDto;
import survey.backend.dto.QuestionFullDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.entities.Answer;
import survey.backend.entities.Question;
import survey.backend.repository.AnswerRepository;
import survey.backend.repository.QuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class QuestionService implements survey.backend.service.QuestionService {

    @Autowired
    private QuestionRepository questionRepository ;

    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<QuestionFullDto> findAll() {
        return StreamUtils.toStream(this.questionRepository.findAllByOrder())
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionFullDto.class))
                .toList();
    }

    @Override
    public Optional<QuestionFullDto> findByIdFullDto(long id) {
        return this.questionRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionFullDto.class));
    }

    @Override
    public Optional<QuestionFullDto> findById(long id) {
        return this.questionRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionFullDto.class));
    }

    @Override
    public QuestionDto add(QuestionDto questionDto) {
        var questionEntity = modelMapper.map(questionDto, Question.class);
        this.questionRepository.save(questionEntity);
        return modelMapper.map(questionEntity, QuestionDto.class);
    }

    @Override
    public Optional<QuestionDto> update(QuestionDto questionDto) {
        return this.questionRepository.findById(questionDto.getId())
                .map(questionEntity -> {
                    // update entity object with DTO fields
                    modelMapper.map(questionDto, questionEntity);
                    // update in DB
                    questionRepository.save(questionEntity);
                    // transform entity updated in DTO
                    return modelMapper.map(questionEntity, QuestionDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        return questionRepository.findById(id)
                .map(questionEntity -> {
                    questionRepository.delete(questionEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<QuestionFullDto> addAnswer(long questionId, long answerId) {


        return questionRepository.findById(questionId)
                .flatMap(questionEntity -> answerRepository.findById(answerId)
                        .map(answerEntity -> {
                             questionEntity.getAnswers().add(answerEntity);
                            questionRepository.save(questionEntity);
                            return modelMapper.map(questionEntity, QuestionFullDto.class);
                        })
                );
    }

    @Override
    public Optional<QuestionFullDto> addAnswers(long questionId, Collection<Long> answerIds) {
        return questionRepository.findById(questionId)
                .flatMap(questionEntity -> {
                    var answerEntities = StreamUtils.toStream(answerRepository.findAllById(answerIds)).toList();
                    if (answerIds.size() != answerEntities.size()) {

                        return Optional.empty();
                    }

                    questionEntity.getAnswers().addAll(answerEntities);

                    questionRepository.save(questionEntity);

                    return Optional.of(modelMapper.map(questionEntity, QuestionFullDto.class));
                });
    }

    @Override
    public Optional<QuestionFullDto> removeAnswer(long questionId, long answerId) {
        return questionRepository.findById(questionId)
                .flatMap(questionEntity -> answerRepository.findById(answerId)
                        .map(answerEntity -> {
                            questionEntity.getAnswers().remove(answerEntity);
                            questionRepository.save(questionEntity);
                            return modelMapper.map(questionEntity, QuestionFullDto.class);
                        })
                );
    }
}
