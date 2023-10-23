package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record RequestDto(
        RequestInfoDto info,
        List<RequestResultsDto> results
) {
}
