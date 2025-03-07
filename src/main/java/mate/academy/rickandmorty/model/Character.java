package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;

    public Character() {
    }

    public Character(String externalId, String name, String status, String gender) {
        this.externalId = externalId;
        this.name = name;
        this.status = status;
        this.gender = gender;
    }
}
