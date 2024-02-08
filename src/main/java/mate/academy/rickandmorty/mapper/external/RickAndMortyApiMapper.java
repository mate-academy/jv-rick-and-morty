package mate.academy.rickandmorty.mapper.external;

import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.model.Character;

public interface RickAndMortyApiMapper {
    Character toDto(CharacterApiDto characterApiDto);
}
