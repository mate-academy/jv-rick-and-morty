package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoResponseDataDto {
    private List<CharacterResponseDto> results;
}
