package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PageInfoDto(
        @JsonProperty("next")
        String nextPageUrl
) {
}
