package mate.academy.rickandmorty.dto;

import java.util.List;

public record CharacterResponseDto(
        InfoDto info,
        List<CharacterApiDto> results
) {
}
