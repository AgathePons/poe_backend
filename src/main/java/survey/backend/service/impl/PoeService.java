package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.components.StreamUtils;
import survey.backend.dto.*;
import survey.backend.entities.Poe;
import survey.backend.repository.PoeRepository;
import survey.backend.repository.TraineeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PoeService implements survey.backend.service.PoeService {

    @Autowired
    private PoeRepository poeRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PoeDto> findAll() {
        return StreamUtils.toStream(this.poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeDto.class))
                .toList();
    }

    @Override
    public List<PoeSurveyDto> findAllWithSurvey() {
        return StreamUtils.toStream(this.poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeSurveyDto.class))
                .toList();
    }

    @Override
    public Optional<PoeFullDto> findById(long id) {
        return this.poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeFullDto.class));
    }
    @Override
    public Optional<PoeSurveyDto> findByIdWithStatus(long id) {
        return this.poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeSurveyDto.class));
    }

    @Override
    public PoeDto add(PoeDto poeDto) {
        var poeEntity = modelMapper.map(poeDto, Poe.class);
        this.poeRepository.save(poeEntity);
        return modelMapper.map(poeEntity, PoeDto.class);
    }

    @Override
    public Optional<PoeDto> update(PoeDto poeDto) {
        return this.poeRepository.findById(poeDto.getId())
                .map(poeEntity -> {
                    // update entity object with DTO fields
                    modelMapper.map(poeDto, poeEntity);
                    // update in DB
                    poeRepository.save(poeEntity);
                    // transform entity updated in DTO
                    return modelMapper.map(poeEntity, PoeDto.class);
                });
    }

    @Override
    public Optional<PoeStatusDto> updateStatus(PoeStatusDto poeSurveyDto) {
        return this.poeRepository.findById(poeSurveyDto.getId())
                .map(poeEntity -> {
                    // update entity object with DTO fields
                    if (poeSurveyDto.getStatus1() != null ) {
                        poeEntity.setStatus1(poeSurveyDto.getStatus1());
                    }
                    if (poeSurveyDto.getStatus6() != null) {
                        poeEntity.setStatus6(poeSurveyDto.getStatus6());
                    }
                    if (poeSurveyDto.getStatus12() != null) {
                        poeEntity.setStatus12(poeSurveyDto.getStatus12());
                    }
                    if (poeSurveyDto.getSentDate1() != null) {
                        poeEntity.setSentDate1(poeSurveyDto.getSentDate1());
                    }
                    if (poeSurveyDto.getSentDate6() != null) {
                        poeEntity.setSentDate6(poeSurveyDto.getSentDate6());
                    }
                    if (poeSurveyDto.getSentDate12() != null) {
                        poeEntity.setSentDate12(poeSurveyDto.getSentDate12());
                    }

                    // update in DB
                    poeRepository.save(poeEntity);
                    // transform entity updated in DTO
                    return modelMapper.map(poeEntity, PoeStatusDto.class);
                });
    }

    @Override
    public Optional<PoeFullDto> addTrainee(long poeId, long traineeId) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> traineeRepository.findById(traineeId)
                        .map(traineeEntity -> {
                            poeEntity.getTrainees().add(traineeEntity);
                            poeRepository.save(poeEntity);
                            return modelMapper.map(poeEntity, PoeFullDto.class);
                        })
                );
    }

    @Override
    public Optional<PoeFullDto> addTrainees(long poeId, Collection<Long> traineeIds) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    var traineeEntities = StreamUtils.toStream(traineeRepository.findAllById(traineeIds)).toList();
                    if (traineeIds.size() != traineeEntities.size()) {
                        // if at least one trainee not found
                        return Optional.empty();
                    }
                    // add trainees
                    poeEntity.getTrainees().addAll(traineeEntities);
                    // save
                    poeRepository.save(poeEntity);
                    // return
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public Optional<PoeFullDto> removeTrainee(long poeId, long traineeId) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> traineeRepository.findById(traineeId)
                        .map(traineeEntity -> {
                            poeEntity.getTrainees().remove(traineeEntity);
                            poeRepository.save(poeEntity);
                            return modelMapper.map(poeEntity, PoeFullDto.class);
                        })
                );
    }

    @Override
    public boolean delete(long id) {
        return poeRepository.findById(id)
                .map(poeEntity -> {
                    poeRepository.delete(poeEntity);
                    return true;
                })
                .orElse(false);
    }
}
