package trangthai1.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    Long id;

   @Column(name = "status_name")
    private String statusname;

   @Column(name = "level")
   private int level;

/*  @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
   private List < User > users = new ArrayList <>();*/
}
