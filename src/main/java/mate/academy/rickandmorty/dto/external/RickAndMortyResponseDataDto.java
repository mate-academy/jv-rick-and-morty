package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record RickAndMortyResponseDataDto(
        RickAndMortyPageInfoDto info,
        @JsonProperty("results") List<RickAndMortyCharacterDto> data
) {
}
