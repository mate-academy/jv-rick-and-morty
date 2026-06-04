package mate.academy.rickandmorty.dto.page;

import java.util.List;
import mate.academy.rickandmorty.dto.character.CreateCharacterRequestDto;

public record PageResponseDto(
        InfoResponseDto info,
        List<CreateCharacterRequestDto> results) {
}
