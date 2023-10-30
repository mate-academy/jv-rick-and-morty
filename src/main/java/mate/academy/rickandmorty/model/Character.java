package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "characters")
@Data
public class Character {
    @Id
    private long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
