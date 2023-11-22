package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    Character toModel(CharacterInfoDto dto);

    CharacterResponseDto toDto(Character character);
}
