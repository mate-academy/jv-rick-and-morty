package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private InfoDto info;

    @JsonProperty("results")
    private List<ExternalCharacterDto> characters;
}
