package mate.academy.rickandmorty.dto.external;

import java.time.LocalDateTime;
import java.util.List;

public record CharacterResponseDataDto(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        OriginResponseDto origin,
        OriginResponseDto location,
        String image,
        List<String> episode,
        String url,
        LocalDateTime created
) {
}
