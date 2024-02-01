package mate.academy.rickandmorty.mapper.character;

import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.dto.character.CharacterFromApiDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CharacterFromApiDto character);
}
