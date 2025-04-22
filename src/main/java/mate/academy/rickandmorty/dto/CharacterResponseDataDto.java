package mate.academy.rickandmorty.dto;

import java.util.List;

public record CharacterResponseDataDto(
        CharacterMetadataDto info,
        List<CharacterRecordDto> results) {
}
