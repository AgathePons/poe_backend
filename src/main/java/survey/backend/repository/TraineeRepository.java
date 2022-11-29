package survey.backend.repository;

import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class TraineeRepository {
  private Set<TraineeDto> trainees;

  public Set<TraineeDto> getTrainees() {
    return this.trainees;
  }

  public Optional<TraineeDto> findById(int id) {
    return this.trainees.stream()
            .filter( trainee -> trainee.getId() == id)
            .findFirst();
  }

  public TraineeRepository() {
    this.createFakeRepository();
  }

  private void createFakeRepository() {
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
    this.trainees = traineeSet;
  }
}
