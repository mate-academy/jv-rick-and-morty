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
    private Long id;
    @Column(name = "externalId", nullable = false)
    private String externalId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "gender", nullable = false)
    private String gender;
}
