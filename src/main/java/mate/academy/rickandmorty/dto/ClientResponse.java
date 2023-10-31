package mate.academy.rickandmorty.dto;

import java.util.List;

public record ClientResponse(
        List<ClientCharacterDto> results
) {
}
