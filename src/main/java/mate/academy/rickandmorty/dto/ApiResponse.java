package mate.academy.rickandmorty.dto;

import java.util.List;

public record ApiResponse(
        ApiPageInfo info,
        List<CharacterDto> results
) {
}
