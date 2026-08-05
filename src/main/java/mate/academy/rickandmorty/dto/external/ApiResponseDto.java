package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponseDto(
        PageInfo info,
        List<ExternalCharacterDto> results
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PageInfo(String next) {}
}

