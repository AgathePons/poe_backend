package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.entities.conerter.BlankStringConverter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="trainee")
@Getter @Setter
public class Trainee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name="birth_date", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @Column(unique = true, nullable = false)
  private  String email;

  @Column(name = "phone_number", nullable = true)
  //@Convert(converter = BlankStringConverter.class)
  private String phoneNumber;
}
