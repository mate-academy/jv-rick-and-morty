package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterDto {
    @JsonProperty("id")
    private long externalId;
    private String name;
    private String status;
    private String gender;
}
