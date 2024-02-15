package mate.academy.rickandmorty.dto;

import lombok.Data;
import java.util.List;

@Data
public class CharacterResponseDataDto {
    List<RickAndMortyCharacterDto> results;
}
