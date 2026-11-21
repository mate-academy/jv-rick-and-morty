package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterPageDto(PageInfoDto info, List<CharacterResponseDto> results) {
}
