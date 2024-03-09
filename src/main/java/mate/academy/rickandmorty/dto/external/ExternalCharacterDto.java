package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalCharacterDto {
    @JsonProperty("id")
    private Integer externalId;
    private String name;
    private String status;
    private String gender;
}

