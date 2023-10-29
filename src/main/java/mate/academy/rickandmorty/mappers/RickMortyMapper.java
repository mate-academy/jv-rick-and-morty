package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.dto.CharacterDtoResponse;
import mate.academy.rickandmorty.models.Character;

public interface RickMortyMapper {
    Character toModel(CharacterDtoResponse rickMortyDtoRequest);

}
