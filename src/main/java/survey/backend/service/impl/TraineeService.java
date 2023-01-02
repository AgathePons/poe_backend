package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.components.StreamUtils;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.TraineeRepository;

import java.util.Optional;

@Service
public class TraineeService implements survey.backend.service.TraineeService {

@Autowired
private TraineeRepository traineeRepository;

@Autowired
private ModelMapper modelMapper;

  @Override
  public Iterable<TraineeDto> findAll() {
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
  public Iterable<TraineeDto> search(String lastName, String firstName) {
    if (lastName != null && firstName != null) {
      return StreamUtils.toStream(this.traineeRepository.listByLastNameFirstName(lastName, firstName))
              .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
              .toList();
    }
    if (lastName != null && firstName == null) {
      return StreamUtils.toStream(this.traineeRepository.findByLastName(lastName))
              .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
              .toList();
    }
    return StreamUtils.toStream(this.traineeRepository.findByFirstName(firstName))
            .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
            .toList();
  }

  @Override
  public TraineeDto add(TraineeDto traineeDto) {
    //return this.traineeRepository.save(traineeDto.toTrainee());
    return null;
  }

  @Override
  public Optional<TraineeDto> update(TraineeDto traineeDto) {
    Trainee trainee = traineeDto.toTrainee();
    Optional<Trainee> oTrainee = this.traineeRepository.findById(trainee.getId());
    if (oTrainee.isPresent()) {
      this.traineeRepository.save(trainee);
      //return Optional.of(trainee);
    }
    //return Optional.empty();
    return null;
  }

  @Override
  public boolean delete(long id) {
    Optional<Trainee> traineeToDelete = this.traineeRepository.findById((long) id);
    if(traineeToDelete.isPresent()) {
      this.traineeRepository.delete(traineeToDelete.get());
      return true;
    }
    return  false;
  }
}
