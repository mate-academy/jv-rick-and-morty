package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    @JsonProperty("info")
    private CharacterInfoDto characterInfoDto;
    @JsonProperty("results")
    private List<CharacterResultDto> characterResultDto;
}
