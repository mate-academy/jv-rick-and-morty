package mate.academy.rickandmorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterRM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;

    @JsonProperty("id")
    @Column(name = "external_id")
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
