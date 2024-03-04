package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterResponseResultsDto(
        @JsonProperty("id")
        int externalId,
        String name,
        String status,
        String gender
) {
}
