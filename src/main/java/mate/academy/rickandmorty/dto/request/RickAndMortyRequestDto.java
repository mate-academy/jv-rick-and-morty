package mate.academy.rickandmorty.dto.request;

import java.util.List;

public record RickAndMortyRequestDto(
        RickAndMortyRequestInfoDto info,
        List<CharacterRequestDto> results
) {
}
