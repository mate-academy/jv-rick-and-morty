package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDto(
        MetaInfoDto info, List<CharacterInfoDto> results) {
}
