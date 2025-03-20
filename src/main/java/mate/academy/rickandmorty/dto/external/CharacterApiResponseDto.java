package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterApiResponseDto(
        InfoResponseDto info,
        @JsonProperty("results")
        List<CharacterResponseDto> characters
) {
}
