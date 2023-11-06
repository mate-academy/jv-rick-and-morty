package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.model.Character;

@Data
public class CharacterResponseDto {
    private ResponseMetadataDto info;
    private List<Character> results;
}
