package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ApiResponseDataDto(
        List<CharacterResponseDto> results,
        InfoDto info) {
}
