package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    Character toCharacter(CharacterDto characterDto);

    CharacterResponseDto toResponseDto(Character character);
}
