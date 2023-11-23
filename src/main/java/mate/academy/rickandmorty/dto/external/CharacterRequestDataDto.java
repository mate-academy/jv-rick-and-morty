package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterRequestDataDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
