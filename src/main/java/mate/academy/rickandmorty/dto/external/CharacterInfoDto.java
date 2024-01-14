package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterInfoDto(
        @JsonProperty("id")
        Long externalId,
        String name,
        String status,
        String gender) {
}
