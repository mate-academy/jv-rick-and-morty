package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(
        ExternalCharacterInfoDto info,
        List<ExternalCharacterDto> results
) {
}
