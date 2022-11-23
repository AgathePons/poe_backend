package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import survey.backend.service.TraineeService;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

  // component to call TraineeController with HTTP requests
  @Autowired
  MockMvc mockMvc;

  @MockBean
  TraineeService traineeService;

  @Test
  void testGetByIdOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/trainee/123")
            .accept(MediaType.APPLICATION_JSON)
    );

  }

  @Test
  void testGetByIdKoNotFound() {
    fail();
  }
}