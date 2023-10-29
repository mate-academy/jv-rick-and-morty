package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RickMortyDtoResponse {
    @JsonProperty(value = "id")
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
