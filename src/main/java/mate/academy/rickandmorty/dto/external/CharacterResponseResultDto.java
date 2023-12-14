package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseResultDto {
    private List<CharacterDto> results;
}
