package mate.academy.rickandmorty.dto.character;

import java.util.List;
import lombok.Data;

@Data
public class CharactersResponseDataDto {
    private CharactersInfo info;
    private List<CharacterResponseDto> results;
}
