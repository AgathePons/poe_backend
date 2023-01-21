package survey.backend.entities;

import lombok.*;
import survey.backend.enums.PoeType;
import survey.backend.enums.Status;

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

  @Column(name="status_1", nullable = false)
  private Boolean status1 = false;

  @Column(name = "sent_date_1", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date sentDate1;

  @Column(name = "link_1", nullable = true)
  private String link1;

  @Column(name="status_6", nullable = false)
  private Boolean status6 = false;

  @Column(name = "sent_date_6", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date sentDate6;

  @Column(name = "link_6", nullable = true)
  private String link6;

  @Column(name="status_12", nullable = false)
  private Boolean status12 = false;

  @Column(name = "sent_date_12", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date sentDate12;

  @Column(name = "link_12", nullable = true)
  private String link12;



}
