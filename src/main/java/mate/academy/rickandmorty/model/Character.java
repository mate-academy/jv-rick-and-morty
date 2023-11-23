package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String externalId;
    @NonNull
    private String name;
    @NonNull
    private String status;
    @NonNull
    private String gender;

    public Character() {
    }
}
