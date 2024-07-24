package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "characters_from_api")
public class CharacterFromApi {
    @Id
    @Column(nullable = false, unique = true)
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
