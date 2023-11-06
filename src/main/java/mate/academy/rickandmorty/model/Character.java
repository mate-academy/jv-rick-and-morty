package mate.academy.rickandmorty.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "characters")
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long internalId;
    @JsonSetter("id")
    private Long externalId;
    private String name;
    private String status;
    private String gender;

    @JsonGetter("id")
    public Long getInternalId() {
        return internalId;
    }

    @JsonGetter("externalId")
    public Long getExternalId() {
        return externalId;
    }
}
