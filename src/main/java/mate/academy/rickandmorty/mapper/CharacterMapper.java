package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    CharacterApiDto toDto(Character character);

    Character toModel(CharacterApiDto dto);
}
