package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterDto(
        @JsonProperty("id")
        Long externalId,
        String name,
        String status,
        String species,
        String type,
        String gender,
        String image,
        String url,
        String created,
        OriginDto origin,
        LocationDto location,
        List<String> episode
) {}
