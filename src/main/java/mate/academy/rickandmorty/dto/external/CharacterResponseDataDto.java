package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(
        CharacterInfoDataDto info,
        List<CreateCharacterRequestDto> results
) {
}
