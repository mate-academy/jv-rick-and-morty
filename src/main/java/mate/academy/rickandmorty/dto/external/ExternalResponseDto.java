package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ExternalResponseDto(
        MetadataDto info,

        List<ExternalCharacterResponseDto> results
) {}
