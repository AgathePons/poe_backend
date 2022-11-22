package survey.backend.service;

import survey.backend.dto.TraineeDto;

import java.util.Optional;
import java.util.Set;

public interface TraineeService {
  Set<TraineeDto> findAll();

  Optional<TraineeDto> findById(int id);

  Set<TraineeDto> search(String lastName, String firstName);

  TraineeDto add(TraineeDto traineeDto);

  TraineeDto update(TraineeDto traineeDto);

  void delete(int id);
}
