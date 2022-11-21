package survey.backend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {
  @Test
  void testDefaultConstructor() {
    var traineeDto = new TraineeDto();
    assertAll(
            () ->  assertNull(traineeDto.getId(), "id"),
            () ->  assertNull(traineeDto.getLastName(), "lastName"),
            () ->  assertNull(traineeDto.getFirstName(), "firstName"),
            () ->  assertNull(traineeDto.getEmail(), "email"),
            () ->  assertNull(traineeDto.getPhoneNumber(), "phoneNumber"),
            () ->  assertNull(traineeDto.getBirthDate(), "birthDate")
    );
  }

  // TODO all args
  // TODO builder
}