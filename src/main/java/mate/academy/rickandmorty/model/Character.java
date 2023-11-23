package mate.academy.rickandmorty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
