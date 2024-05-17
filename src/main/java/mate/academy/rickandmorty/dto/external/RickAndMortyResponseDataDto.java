package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record RickAndMortyResponseDataDto(
        ApiResponseInfo info,
        List<CharacterApiResponseDto> results
) {
}
