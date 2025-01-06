package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import mate.academy.rickandmorty.model.Character;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalCharacterDto {
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private Character.Status status;
    private Character.Gender gender;
}
