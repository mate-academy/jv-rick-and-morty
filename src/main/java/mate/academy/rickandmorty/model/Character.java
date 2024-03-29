package mate.academy.rickandmorty.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "characters")
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
    private String image;
    private String url;
    private String created;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "origins_id")
    private Origin origin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locations_id")
    private Location location;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> episode;
}
