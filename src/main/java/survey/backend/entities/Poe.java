package survey.backend.entities;

import lombok.*;
import survey.backend.enums.PoeType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="poe")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "trainees")
public class Poe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(length = 150, nullable = false)
  private String title;

  @Column(name = "begin_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date beginDate;

  @Column(name = "end_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @Column(name="poe_type" ,length = 10, nullable = false)
  @Enumerated(EnumType.STRING)
  private PoeType type;

  @OneToMany // mode fetch LAZY by default
  @JoinColumn(name="poe_id")
  @Builder.Default
  private Set<Trainee> trainees = new HashSet<>();
}
