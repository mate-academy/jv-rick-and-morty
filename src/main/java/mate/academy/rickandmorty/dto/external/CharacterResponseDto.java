package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import mate.academy.rickandmorty.model.Character;

import java.util.List;

@Data
public class CharacterResponseDto {
    private ResponseMetadataDto info;
    private List<Character> results;
}
