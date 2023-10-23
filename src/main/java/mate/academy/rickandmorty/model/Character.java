package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "characters")
public class Character {
    @Id
    private Long id;
    @Column(name = "external_id")
    private int externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
}
