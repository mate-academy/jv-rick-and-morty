package mate.academy.rickandmorty.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(unique = true)
    private String name;
    @NonNull
    private String status;
    @NonNull
    private String gender;

    public Character() {
    }
}
