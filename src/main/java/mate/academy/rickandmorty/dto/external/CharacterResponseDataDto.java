package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterResponseDataDto(
        @JsonProperty("info")
        PageInfoDto pageInfoDto,
        @JsonProperty("results")
        List<CharacterResponseDto> responseDtos
) {
}
