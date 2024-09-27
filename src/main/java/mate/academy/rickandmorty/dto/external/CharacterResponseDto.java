package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public final class CharacterResponseDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
