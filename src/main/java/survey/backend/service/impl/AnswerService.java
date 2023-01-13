package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.components.StreamUtils;
import survey.backend.dto.AnswerDto;
import survey.backend.dto.QuestionFullDto;
import survey.backend.entities.Answer;
import survey.backend.entities.Question;
import survey.backend.repository.AnswerRepository;
import survey.backend.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
@Service
public class AnswerService implements survey.backend.service.AnswerService {

    @Autowired
    private AnswerRepository answerRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<AnswerDto> findAll() {
        return StreamUtils.toStream(this.answerRepository.findAll())
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class))
                .toList();
    }

    @Override
    public Optional<AnswerDto> findById(long id) {
        return this.answerRepository.findById(id)
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class));
    }

    @Override
    public AnswerDto add(AnswerDto answerDto) {
        var answerEntity = modelMapper.map(answerDto, Answer.class);
        this.answerRepository.save(answerEntity);
        return modelMapper.map(answerEntity, AnswerDto.class);
    }

    @Override
    public Optional<AnswerDto> update(AnswerDto answerDto) {
        return this.answerRepository.findById(answerDto.getId())
                .map(answerEntity -> {
                    // update entity object with DTO fields
                    modelMapper.map(answerDto, answerEntity);
                    // update in DB
                    answerRepository.save(answerEntity);
                    // transform entity updated in DTO
                    return modelMapper.map(answerEntity, AnswerDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        return answerRepository.findById(id)
                .map(answerEntity -> {
                    answerRepository.delete(answerEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<AnswerDto> addQuestion(long questionId, long answerId) {

        return answerRepository.findById(answerId)
                .flatMap(answerEntity -> questionRepository.findById(questionId)
                        .map(questionEntity -> {
                            answerEntity.getQuestions().add(questionEntity);

                            answerRepository.save(answerEntity);
                            return modelMapper.map(answerEntity, AnswerDto.class);
                        })
                );
    }
}
