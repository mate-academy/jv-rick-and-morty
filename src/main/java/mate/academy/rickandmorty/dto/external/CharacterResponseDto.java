package mate.academy.rickandmorty.dto.external;

import java.util.List;
import java.util.Map;

public record CharacterResponseDto(
        Map<String, Object> info,
        List<CharacterExternalDto> results) {
}
