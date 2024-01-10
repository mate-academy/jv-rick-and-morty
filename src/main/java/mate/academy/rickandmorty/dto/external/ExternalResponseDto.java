package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ExternalResponseDto(
        MetadataDto info,

        List<ExternalCharacterResponseDto> results
) {}
