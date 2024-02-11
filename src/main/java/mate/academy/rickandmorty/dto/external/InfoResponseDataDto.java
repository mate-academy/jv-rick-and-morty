package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class InfoResponseDataDto {
    private List<CharacterResponseDto> results;
}
