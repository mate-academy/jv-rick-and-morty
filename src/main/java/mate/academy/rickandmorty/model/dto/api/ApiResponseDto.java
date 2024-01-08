package mate.academy.rickandmorty.model.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiResponseDto(
        InfoDto info,
        @JsonProperty("results")
        List<ApiCharacterResponseDto> characters
) {
}
