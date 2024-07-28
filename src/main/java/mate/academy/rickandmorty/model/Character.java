package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private LocalDateTime created;
}
