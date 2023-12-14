package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import java.util.List;

@Data
public class CharactersResponseDataDto {
    private CharactersInfo info;
    private List<CharacterResponseInfoDto> results;
}
