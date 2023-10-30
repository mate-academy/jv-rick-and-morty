package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ExternalResponseDto(
        Info info,
        @JsonProperty("results") List<Result> resultList
) {
}
