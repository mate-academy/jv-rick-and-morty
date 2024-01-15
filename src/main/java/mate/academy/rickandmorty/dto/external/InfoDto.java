package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InfoDto(
        @JsonProperty("count") int count,
        @JsonProperty("pages") int pages,
        @JsonProperty("next") String next,
        @JsonProperty("prev") String prev) {
}
