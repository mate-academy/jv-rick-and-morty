package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record RickAndMortyApiResponseDataDto(
        RickAndMortyApiInfoDto info,
        List<RickAndMortyApiCharacterDto> results
) {
}
