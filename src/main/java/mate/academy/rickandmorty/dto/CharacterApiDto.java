package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterApiDto {
    @JsonProperty("id")
    private Long externalId;

    private String name;

    private String status;

    private String gender;
}
