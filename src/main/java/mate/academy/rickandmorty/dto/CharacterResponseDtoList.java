package mate.academy.rickandmorty.dto;

import java.util.List;

public record CharacterResponseDtoList(MetaInfo info, List<CharacterResponseDto> results) {
}
