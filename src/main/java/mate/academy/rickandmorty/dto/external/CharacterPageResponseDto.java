package mate.academy.rickandmorty.dto.external;

import java.util.Set;

public record CharacterPageResponseDto(PageInfoDto info, Set<CharacterInfoDto> results) {
}
