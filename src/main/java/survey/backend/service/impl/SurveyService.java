package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import survey.backend.components.StreamUtils;
import survey.backend.dto.PoeDto;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.entities.Survey;
import survey.backend.repository.QuestionRepository;
import survey.backend.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

public class SurveyService implements survey.backend.service.SurveyService{

    @Autowired
    private SurveyRepository surveyRepository ;

    private QuestionRepository questionRepository ;

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
        return false;
    }
}
