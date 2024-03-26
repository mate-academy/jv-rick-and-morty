package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class CharacterPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            name = "external_id"
    )
    private Long externalId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String status;
    private String gender;
}
