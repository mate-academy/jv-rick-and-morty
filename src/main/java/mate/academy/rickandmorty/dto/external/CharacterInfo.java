package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;



public record CharacterInfo (
        @JsonProperty("id")
        String externalId,
        String name,
        String status,
        String gender){
}
