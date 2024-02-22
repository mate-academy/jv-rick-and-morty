package mate.academy.rickandmorty.dto.external;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record CharacterExternalResponseDto(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        LocationResponseDto origin,
        LocationResponseDto location,
        String image,
        ArrayList<String> episode,
        String url,
        LocalDateTime created
) {
}
