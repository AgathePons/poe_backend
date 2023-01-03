package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Trainee;

import java.util.List;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {

  // Native SQL
  @Query(
          value = "SELECT * FROM trainee WHERE last_name = :lastName AND first_name = :firstName",
          nativeQuery = true
  )
  List<Trainee> listByLastNameFirstName(
          @Param(value="lastName") String lastName,
          @Param(value = "firstName") String firstName
  );

  // JPQL/HQL
  @Query(
          value = "SELECT t FROM Trainee t WHERE t.lastName = :lastName AND t.firstName = :firstName"
  )
  List<Trainee> anotherListByLastNameFirstName(
          @Param(value="lastName") String lastName,
          @Param(value = "firstName") String firstName
  );

//  @Query(
//          value = "SELECT * FROM trainee WHERE last_name = :lastName",
//          nativeQuery = true
//  )
//  public Iterable<Trainee> listByLastName(
//          @Param(value="lastName") String lastName
//  );
//
//  @Query(
//          value = "SELECT * FROM trainee WHERE first_name = :firstName",
//          nativeQuery = true
//  )
//  public Iterable<Trainee> listByFirstName(
//          @Param(value = "firstName") String firstName
//  );

  // jpa provides findByFoo(foo) methods to find by attributes
  List<Trainee> findByLastName(String lastName);

  List<Trainee> findByFirstName(String firstName);

}
