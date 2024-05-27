package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCharacterRequestDto(
        @JsonProperty("id")
        String externalId,
        String name,
        String status,
        String gender
) {
}
