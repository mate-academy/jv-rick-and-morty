package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(PageInfoDto infoDto,
                                       List<CharacterResultDto> resultsDto) {
}
