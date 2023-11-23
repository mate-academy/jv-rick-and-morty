package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterRequest {
    private List<CharacterRequestDataDto> results;
}
