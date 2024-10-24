package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResponseDto(InfoDto info,
                          @JsonProperty("results")
                          List<CreateCharacterDto> characters) {
}
