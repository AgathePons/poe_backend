package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.FakeTraineeRepository;
import survey.backend.repository.TraineeRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class TraineeService implements survey.backend.service.TraineeService {

@Autowired
private TraineeRepository traineeRepository;

  @Override
  public Iterable<Trainee> findAll() {
    return this.traineeRepository.findAll();
  }

  @Override
  public Optional<Trainee> findById(int id) {
    return this.traineeRepository.findById((long) id);
  }

  @Override
  public Iterable<Trainee> search(String lastName, String firstName) {
    // TODO implement
    return null;
  }

  @Override
  public Trainee add(TraineeDto traineeDto) {
    return null;
//    return this.traineeRepository.save(traineeDto);
  }

  @Override
  public Optional<Trainee> update(TraineeDto traineeDto) {
    return Optional.empty();
  }

  @Override
  public boolean delete(int id) {
    Optional<Trainee> traineeToDelete = this.traineeRepository.findById((long) id);
    if(traineeToDelete.isPresent()) {
      this.traineeRepository.delete(traineeToDelete.get());
      return true;
    }
    return  false;
  }
}
