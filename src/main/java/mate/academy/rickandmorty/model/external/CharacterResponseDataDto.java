package mate.academy.rickandmorty.model.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private List<CharacterResultDto> results;
}
