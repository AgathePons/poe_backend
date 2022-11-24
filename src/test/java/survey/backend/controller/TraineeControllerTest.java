package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.util.Optional;

// alt + enter to import static to avoid writ all MockMvcRequestBuilders ...
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

  final static String BASE_URL = "/api/trainee";

  // component to call TraineeController with HTTP requests
  @Autowired
  MockMvc mockMvc;

  @MockBean
  TraineeService traineeService;

  @Test
  void testGetByIdOk() throws Exception {
    // given
    int id = 123;
    String lastname = "Doe";
    String firstname = "John";
    var traineeDto = TraineeDto.builder()
            .id(id)
            .lastName(lastname)
            .firstName(firstname).build();

    BDDMockito.given(traineeService.findById(id))
            .willReturn(Optional.of(traineeDto));
    // when
    mockMvc.perform(get(BASE_URL + "/" + id)
            .accept(MediaType.APPLICATION_JSON)
            )
    // then: verify HTTP communication
            .andDo(print()) // log req / res
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.lastName").value(lastname))
            .andExpect(jsonPath("$.firstName").value(firstname));

    // then: verify trainee service is called
    BDDMockito.then(traineeService)
            .should()
            .findById(id);
  }

  @Test
  void testGetByIdKoNotFound() throws Exception {
    // given
    int id = 0;
    BDDMockito.given(traineeService.findById(id))
            .willReturn(Optional.empty());

    // when
    mockMvc.perform(get(BASE_URL + "/" + id)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // then: verify HTTP communication
            .andDo(print()) // log req / res
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Trainee with id " + id + " not found"));

    // then: verify trainee service is called
    BDDMockito.then(traineeService)
            .should()
            .findById(id);
  }
}