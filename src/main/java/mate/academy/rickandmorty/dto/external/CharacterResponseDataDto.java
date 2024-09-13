package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(
        PageInfoDto info,
        List<CharacterResponseDto> results
) {}
