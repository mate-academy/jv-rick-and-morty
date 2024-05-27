package mate.academy.rickandmorty.dto.external;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;

public record CharacterResponseDataDto(
        CharacterMetadataDto info,
        List<CreateCharacterRequestDto> results
) {
}

