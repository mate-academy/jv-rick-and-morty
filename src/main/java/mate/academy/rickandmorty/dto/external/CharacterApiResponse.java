package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterApiResponse(
        CharacterApiInfoDto info,
        List<CharacterResponseResultsDto> results
) {}
