package mate.academy.rickandmorty.mapper.internal;

import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    CharacterDto toModel(Character character);

    Character toDto(CharacterDto characterDto);
}
