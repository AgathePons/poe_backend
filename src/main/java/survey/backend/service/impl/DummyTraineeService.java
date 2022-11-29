package survey.backend.service.impl;

import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.repository.TraineeRepository;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class DummyTraineeService implements TraineeService {

  private TraineeRepository repository = new TraineeRepository();

  @Override
  public Set<TraineeDto> findAll() {
    return this.repository.getTrainees();
  }

  @Override
  public Optional<TraineeDto> findById(int id) {
    return this.repository.findById(id);
  }

  @Override
  public Set<TraineeDto> search(String lastName, String firstName) {
    final String DEFAULT_FAMILY = "Found";
        var trainee1 = TraineeDto.builder()
            .id(1)
            .firstName(Objects.isNull(firstName) ? DEFAULT_FAMILY : firstName)
            .lastName(Objects.isNull(lastName) ? "Funny" : lastName)
            .build();
    var trainee2 = TraineeDto.builder()
            .id(2)
            .firstName("Jim")
            .lastName(DEFAULT_FAMILY)
            .build();
    var trainee3 = TraineeDto.builder()
            .id(3)
            .firstName("James")
            .lastName(DEFAULT_FAMILY)
            .build();
    var traineeSet = Set.of(trainee1, trainee2, trainee3);
    return traineeSet;
  }

  @Override
  public TraineeDto add(TraineeDto traineeDto) {
    return this.repository.add(traineeDto);
  }

  @Override
  public Optional<TraineeDto> update(TraineeDto traineeDto) {
    if (traineeDto.getId() == 0) {
      return Optional.empty();
    } else {
      var trainee1 = TraineeDto.builder()
              .id(666)
              .firstName("John")
              .lastName("Doe")
              .email("johndoe@mail.com")
              .phoneNumber("06 55 88 99 66")
              .birthDate(LocalDate.of(1900, 7, 1))
              .build();
      trainee1.setFirstName(traineeDto.getFirstName());
      trainee1.setLastName(traineeDto.getLastName());
      trainee1.setEmail(traineeDto.getEmail());
      trainee1.setPhoneNumber(traineeDto.getPhoneNumber());
      trainee1.setBirthDate(traineeDto.getBirthDate());
      return Optional.of(traineeDto);
    }
  }

  @Override
  public boolean delete(int id) {
    Optional<TraineeDto> traineeToDelete = this.repository.findById(id);
    if(traineeToDelete.isPresent()) {
      return this.repository.delete(traineeToDelete.get());
    }
    return  false;
  }
}
