package mate.academy.rickandmorty.dto.external;

import java.util.ArrayList;

public record ResultsResponseDto(
        ArrayList<CharacterExternalResponseDto> results,
        InfoResponseDto info
) {
}
