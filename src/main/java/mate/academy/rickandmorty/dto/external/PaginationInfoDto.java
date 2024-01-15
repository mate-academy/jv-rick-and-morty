package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PaginationInfoDto(
        @JsonProperty("info")
        InfoDto info,
        @JsonProperty("results")
        List<CharacterInfoDto> results) {
}
