package mate.academy.rickandmorty.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class CharacterResultDto {
    @JsonProperty("id")
    private final int externalId;
    private final String name;
    private final String status;
    private final String gender;

}
