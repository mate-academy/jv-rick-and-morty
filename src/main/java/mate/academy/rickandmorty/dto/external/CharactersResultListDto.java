package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharactersResultListDto {
    private List<CharacterDto> results;
    private InfoDto info;
}
