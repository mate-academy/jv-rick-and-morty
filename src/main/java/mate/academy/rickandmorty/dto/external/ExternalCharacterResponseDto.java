package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.entity.Character;

@Data
public class ExternalCharacterResponseDto {
    private List<Character> results;
    private PageInfo info;
}
