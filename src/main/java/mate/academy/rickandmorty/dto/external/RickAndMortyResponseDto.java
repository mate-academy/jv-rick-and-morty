package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyResponseDto {
    private PageInfoDto info;
    @JsonProperty("results")
    private List<CharacterModelDto> charactersInfo;
}
