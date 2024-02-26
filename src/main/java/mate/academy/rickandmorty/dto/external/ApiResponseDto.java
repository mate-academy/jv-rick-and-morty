package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiResponseDto(
        Info info,
        @JsonProperty("results") List<CharacterDataResponseDto> data) {

}
