package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
public class RickAndMortyCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "external_id")
    private String externalId;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private String type;
    @Column(name = "gender")
    private String gender;
    @Column(name = "image")
    private String image;
}
