package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ResultsResponseDataDto(
        List<CharacterResponseDataDto> results,
        InfoResponseDto infoResponseDto
) {
}
