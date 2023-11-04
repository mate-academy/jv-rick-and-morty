package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record PageResponseDto(
        InfoResponseDto info,
        List<ResultResponseDto> results
) {
}
