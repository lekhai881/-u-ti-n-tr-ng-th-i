package trangthai1.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "note")
    private String note;

    @Column(name = "time_update")
    private String timeUpdate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public User(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
