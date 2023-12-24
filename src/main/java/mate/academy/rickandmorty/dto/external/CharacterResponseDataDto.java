package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(List<CharacterResponseDto> results,
                                       InfoResponseDataDto info) {
}
