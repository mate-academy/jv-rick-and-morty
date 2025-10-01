package mate.academy.rickandmorty.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "characters")
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId;

    private String name;

    private String status;
    private String species;
    private String type;
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    private Location origin;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "character_episodes",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private List<Episode> episodes;
    private String url;
    private OffsetDateTime created;


}
