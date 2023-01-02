package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

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

  @Test
  void testAllArgsConstructor() {
    long id = 666;
    String lastname = "Durand";
    String firstname = "Jean";
    String email = "jeanmi@mail.com";
    String phoneNumber = "0625487556";
    var birthdate = new Date(1975, 7, 12);
    var traineeDto = new TraineeDto(id, lastname, firstname, email, phoneNumber, birthdate);
    assertAll(
            () -> assertEquals(id, traineeDto.getId(), "id"),
            () -> assertEquals(lastname, traineeDto.getLastName(), "lastname"),
            () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
            () -> assertEquals(email, traineeDto.getEmail(), "email"),
            () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "phoneNumber"),
            () -> assertEquals(birthdate, traineeDto.getBirthDate(), "birthdate")
    );
  }

  @Test
  void testBuilder() {
    String firstname = "Jean";
    String email = "jeanmi@mail.com";
    var birthdate = new Date(1975, 7, 5);
    var traineeDto = TraineeDto.builder()
            .firstName(firstname)
            .email(email)
            .birthDate(birthdate)
            .build();
    assertAll(
            () -> assertNull(traineeDto.getId(), "id"),
            () -> assertNull(traineeDto.getLastName(), "lastName"),
            () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
            () -> assertEquals(email, traineeDto.getEmail(), "email"),
            () -> assertNull(traineeDto.getPhoneNumber(), "phoneNumber"),
            () -> assertEquals(birthdate, traineeDto.getBirthDate(), "birthdate")
    );
  }
}