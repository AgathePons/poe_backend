package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.components.StreamUtils;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.entities.Survey;
import survey.backend.repository.QuestionRepository;
import survey.backend.repository.SurveyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService implements survey.backend.service.SurveyService{

    @Autowired
    private SurveyRepository surveyRepository ;

    @Autowired
    private QuestionRepository questionRepository ;
    @Autowired
    private ModelMapper modelMapper ;

    @Override
    public List<SurveyDto> findAll() {
        return StreamUtils.toStream(this.surveyRepository.findAll())
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyDto.class))
                .toList();
    }

    @Override
    public Optional<SurveyFullDto> findById(long id) {
        return this.surveyRepository.findById(id)
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyFullDto.class));
    }

    @Override
    public SurveyDto add(SurveyDto surveyDto) {
        var surveyEntity = modelMapper.map(surveyDto, Survey.class);
        this.surveyRepository.save(surveyEntity);
        return modelMapper.map(surveyEntity, SurveyDto.class);
    }

    @Override
    public Optional<SurveyDto> update(SurveyDto surveyDto) {
        return this.surveyRepository.findById(surveyDto.getId())
                .map(surveyEntity -> {
                    // update entity object with DTO fields
                    modelMapper.map(surveyDto, surveyEntity);
                    // update in DB
                    surveyRepository.save(surveyEntity);
                    // transform entity updated in DTO
                    return modelMapper.map(surveyEntity, SurveyDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        return surveyRepository.findById(id)
                .map(surveyEntity -> {
                    surveyRepository.delete(surveyEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<SurveyFullDto> addQuestion(long surveyId, long questionId) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> questionRepository.findById(questionId)
                        .map(questionEntity -> {
                            surveyEntity.getQuestions().add(questionEntity);
                            surveyRepository.save(surveyEntity);
                            return modelMapper.map(surveyEntity, SurveyFullDto.class);
                        })
                );
    }

    @Override
    public Optional<SurveyFullDto> addQuestions(long surveyId, Collection<Long> questionIds) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> {
                    var questionEntities = StreamUtils.toStream(questionRepository.findAllById(questionIds)).toList();
                    if (questionIds.size() != questionEntities.size()) {
                        // if at least one trainee not found
                        return Optional.empty();
                    }
                    // add trainees
                    surveyEntity.getQuestions().addAll(questionEntities);
                    // save
                    surveyRepository.save(surveyEntity);
                    // return
                    return Optional.of(modelMapper.map(surveyEntity, SurveyFullDto.class));
                });
    }

    @Override
    public Optional<SurveyFullDto> removeQuestion(long surveyId, long questionId) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> questionRepository.findById(questionId)
                        .map(questionEntity -> {
                            surveyEntity.getQuestions().remove(questionEntity);
                            surveyRepository.save(surveyEntity);
                            return modelMapper.map(surveyEntity, SurveyFullDto.class);
                        })
                );
    }
}
