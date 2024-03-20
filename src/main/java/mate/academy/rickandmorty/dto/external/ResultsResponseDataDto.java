package mate.academy.rickandmorty.dto.external;

import java.util.ArrayList;

public record ResultsResponseDataDto(
        ArrayList<CharacterResponseDataDto> results,
        InfoResponseDto infoResponseDto
) {
}
