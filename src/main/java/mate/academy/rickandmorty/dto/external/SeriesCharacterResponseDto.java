package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record SeriesCharacterResponseDto(
        ResponseInfoDto info,
        List<ResponseSeriesCharacterDataDto> results
) {}
