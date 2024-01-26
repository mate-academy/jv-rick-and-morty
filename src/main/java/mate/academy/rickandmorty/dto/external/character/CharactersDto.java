package mate.academy.rickandmorty.dto.external.character;

import lombok.Data;
import mate.academy.rickandmorty.model.Info;

@Data
public class CharactersDto {
    private Info info;
    private CharacterDto[] results;
}
