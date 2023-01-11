package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import survey.backend.components.StreamUtils;
import survey.backend.dto.QuestionDto;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Question;
import survey.backend.entities.Trainee;
import survey.backend.repository.QuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class QuestionService implements survey.backend.service.QuestionService {

    @Autowired
    private QuestionRepository questionRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    @Override
    public List<QuestionDto> findAll() {
        return StreamUtils.toStream(this.questionRepository.findAll())
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
                .toList();
    }

    @Override
    public Optional<QuestionDto> findById(long id) {
        return this.questionRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class));
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
}
