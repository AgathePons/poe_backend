package survey.backend.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import survey.backend.entities.Poe;
import survey.backend.tools.PoeType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@ActiveProfiles("testu")
public class PoeRepositoryTest {
  @Autowired
  PoeRepository poeRepository;

  @Test
  @SneakyThrows
  void testFindByEndingInRange() {
    // given
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = df.parse("2022-01-01");
    Date date2 = df.parse("2022-12-31");
    var poes = List.of(
            Poe.builder()
                    .title("Java Fullstack 1")
                    .beginDate(df.parse("2021-01-01"))
                    .endDate(df.parse("2021-06-01"))
                    .type(PoeType.POEC)
                    .build(),
            Poe.builder()
                    .title("Java Fullstack 2")
                    .beginDate(df.parse("2021-10-01"))
                    .endDate(date1)
                    .type(PoeType.POEC)
                    .build(),
            Poe.builder()
                    .title("Java Fullstack 3")
                    .beginDate(df.parse("2022-03-01"))
                    .endDate(df.parse("2022-06-01"))
                    .type(PoeType.POEC)
                    .build(),
            Poe.builder()
                    .title("Java Fullstack 4")
                    .beginDate(df.parse("2022-10-01"))
                    .endDate(date2)
                    .type(PoeType.POEC)
                    .build(),
            Poe.builder()
                    .title("Java Fullstack 5")
                    .beginDate(df.parse("2022-10-01"))
                    .endDate(df.parse("2023-01-01"))
                    .type(PoeType.POEC)
                    .build()
    );
    System.out.println(poes.toString());

    // when
    var res = poeRepository.findByEndingInRange(date1, date2);
    System.out.println(res);
  }
}
