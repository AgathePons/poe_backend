package survey.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class PoeRepositoryTest {
  @Autowired
  PoeRepository poeRepository;

  @Test
  Date date1 = new Date();
  Date date2 = new Date();
  var res = poeRepository.findByEndingInRange(date1, date2);
}
