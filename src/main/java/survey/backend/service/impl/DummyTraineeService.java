package survey.backend.service.impl;

import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class DummyTraineeService implements TraineeService {

  @Override
  public Set<TraineeDto> findAll() {
    var trainee1 = TraineeDto.builder()
            .id(1)
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1945, 5, 3))
            .build();
    var trainee2 = TraineeDto.builder()
            .id(2)
            .firstName("Jane")
            .lastName("Doe")
            .birthDate(LocalDate.of(1900, 12, 24))
            .build();
    var trainee3 = TraineeDto.builder()
            .id(3)
            .firstName("Micheline")
            .lastName("Duduche")
            .birthDate(LocalDate.of(2001, 11, 18))
            .build();
    var trainee4 = TraineeDto.builder()
            .id(4)
            .firstName("Jean-Michèle")
            .lastName("Apeuprai")
            .birthDate(LocalDate.of(1990, 6, 30))
            .build();
    var trainee5 = TraineeDto.builder()
            .id(4)
            .firstName("Philomène")
            .lastName("Dupuy")
            .birthDate(LocalDate.of(1650, 9, 1))
            .build();
    var traineeSet = Set.of(trainee1, trainee2, trainee3, trainee4, trainee5);
    return traineeSet;
  }

  @Override
  public Optional<TraineeDto> findById(int id) {
    if (id == 0) {
      return Optional.empty();
    } else {
      var trainee1 = TraineeDto.builder()
              .id(id)
              .firstName("John")
              .lastName("Doe")
              .birthDate(LocalDate.of(1945, 5, 3))
              .build();
      return Optional.of(trainee1);
    }
  }

  @Override
  public Set<TraineeDto> search(String lastName, String firstName) {
        var trainee1 = TraineeDto.builder()
            .id(1)
            .firstName(Objects.isNull(firstName) ? "Found" : firstName)
            .lastName(Objects.isNull(lastName) ? "Funny" : lastName)
            .build();
    var trainee2 = TraineeDto.builder()
            .id(2)
            .firstName("Jim")
            .lastName("Found")
            .build();
    var trainee3 = TraineeDto.builder()
            .id(3)
            .firstName("James")
            .lastName("Found")
            .build();
    var traineeSet = Set.of(trainee1, trainee2, trainee3);
    return traineeSet;
  }

  @Override
  public TraineeDto add(TraineeDto traineeDto) {
    traineeDto.setId(666);
    return traineeDto;
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
    if (id == 0) {
      return false;
    } else {
      return true;
    }
  }
}
