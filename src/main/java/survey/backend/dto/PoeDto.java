package survey.backend.dto;

import lombok.*;

import survey.backend.entities.Poe;

import survey.backend.tools.PoeType;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeDto {
  private Long id;
  private String title;

  private Date beginDate;
  private Date endDate;
  private PoeType type;

  public Poe toPoe(){
    Poe poe = new Poe();

    poe.setType(this.type);
    poe.setId(this.id);
    poe.setTitle(this.title);
    poe.setBeginDate(this.beginDate);
    poe.setEndDate(this.endDate);

    return poe;


  }

}
