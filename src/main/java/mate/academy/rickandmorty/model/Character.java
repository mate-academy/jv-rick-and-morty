package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
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
    Long id;
    @Column(nullable = false)
    String externalId;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String status;
    @Column(nullable = false)
    Gender gender;

    public enum Gender {
        Female, Male, Genderless, unknown;
    }
}
