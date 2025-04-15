package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private List<RickAndMortyApiCharacterDto> results;
    private InfoDto info;
}
