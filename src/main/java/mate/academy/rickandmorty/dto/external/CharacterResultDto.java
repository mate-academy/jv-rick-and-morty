package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterResultDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String type;
    private String gender;
    private String image;
}
