package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterDataResponseDto(InfoResponseDto info,
                                       List<CharacterResponseDto> results) {
}
