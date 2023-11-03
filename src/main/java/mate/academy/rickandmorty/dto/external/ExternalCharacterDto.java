package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ExternalCharacterDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        ExternalCharacterLocationDto origin,
        ExternalCharacterLocationDto location,
        String image,
        List<String> episode,
        String url,
        String created
) {
}
