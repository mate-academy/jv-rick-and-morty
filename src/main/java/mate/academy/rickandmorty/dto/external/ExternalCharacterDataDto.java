package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ExternalCharacterDataDto(
        ExternalCharacterInfoDto info,
        List<ExternalCharacterDto> results
) {
}
