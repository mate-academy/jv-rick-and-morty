package mate.academy.rickandmorty.dtoexternal;

import java.util.List;

public record CharacterResponseDataDto(
        CharacterMetadataDto info,
        List<CharacterRecordDto> results) {
}
