package mate.academy.rickandmorty.dto.external;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record CharacterExternalResponseDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        OriginResponseDto origin,
        OriginResponseDto location,
        String image,
        ArrayList<String> episode,
        String url,
        LocalDateTime created) {
}
