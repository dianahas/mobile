package hotels.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Diana on 07-Nov-17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    private String name;
    private String location;
}
