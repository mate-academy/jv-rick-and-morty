package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record RickandmortyapiResponseDataDto(
        RickandmortyapiInfoDto info,
        List<RickandmortyapiCharacterDto> results
) {
}
