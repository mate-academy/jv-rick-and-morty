package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ListOfCharacterResponseDto {
    @JsonProperty("results")
    private List<CharacterDto> characters;
    private InfoDto info;
}
