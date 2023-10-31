package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterResponseDto(@JsonProperty("id") Long externalId,
                                   String name,
                                   String status,
                                   String gender) {
}
