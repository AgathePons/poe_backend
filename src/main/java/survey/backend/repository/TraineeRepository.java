package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {

  // Native SQL
  @Query(
          value = "SELECT * FROM trainee WHERE last_name = :lastName AND first_name = :firstName",
          nativeQuery = true
  )
  Iterable<Trainee> listByLastNameFirstName(
          @Param(value="lastName") String lastName,
          @Param(value = "firstName") String firstName
  );

  // JPQL/HQL
  @Query(
          value = "SELECT t FROM Trainee t WHERE t.lastName = :lastName AND t.firstName = :firstName"
  )
  Iterable<Trainee> anotherListByLastNameFirstName(
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
  Iterable<Trainee> findByLastName(String lastName);

  Iterable<Trainee> findByFirstName(String firstName);

}
