package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ResponseDataDto(ResponseInfoDto info,
                              List<ResponseCharacterDto> results) {
}
