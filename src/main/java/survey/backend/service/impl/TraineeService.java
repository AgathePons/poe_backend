package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.components.StreamUtils;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.TraineeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService implements survey.backend.service.TraineeService {

@Autowired
private TraineeRepository traineeRepository;

@Autowired
private ModelMapper modelMapper;

  @Override
  public List<TraineeDto> findAll() {
    return StreamUtils.toStream(this.traineeRepository.findAll())
            .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
            .toList();
  }

  @Override
  public Optional<TraineeDto> findById(long id) {
    return this.traineeRepository.findById(id)
            .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class));
  }

  @Override
  public List<TraineeDto> search(String lastName, String firstName) {
    if (lastName != null && firstName != null) {
      return StreamUtils.toStream(this.traineeRepository.listByLastNameFirstName(lastName, firstName))
              .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
              .toList();
    }
    if (lastName != null && firstName == null) {
      return StreamUtils.toStream(this.traineeRepository.findByLastNameIgnoreCase(lastName))
              .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
              .toList();
    }
    return StreamUtils.toStream(this.traineeRepository.findByFirstNameIgnoreCase(firstName))
            .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
            .toList();
  }

  @Override
  public TraineeDto add(TraineeDto traineeDto) {
    var traineeEntity = modelMapper.map(traineeDto, Trainee.class);
    this.traineeRepository.save(traineeEntity);
    return modelMapper.map(traineeEntity, TraineeDto.class);
  }

  @Override
  public Optional<TraineeDto> update(TraineeDto traineeDto) {
    return this.traineeRepository.findById(traineeDto.getId())
            .map(traineeEntity -> {
              // update entity object with DTO fields
              modelMapper.map(traineeDto, traineeEntity);
              // update in DB
              traineeRepository.save(traineeEntity);
              // transform entity updated in DTO
              return modelMapper.map(traineeEntity, TraineeDto.class);
            });
  }

  @Override
  public boolean delete(long id) {
    Optional<Trainee> traineeToDelete = this.traineeRepository.findById(id);
    if(traineeToDelete.isPresent()) {
      this.traineeRepository.delete(traineeToDelete.get());
      return true;
    }
    return  false;
  }
}
