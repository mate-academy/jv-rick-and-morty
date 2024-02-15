package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RickAndMortyCharacterDto {
    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private String status;
    private String type;
    private String gender;
    private String image;
}
