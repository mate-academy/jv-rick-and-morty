package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.model.CharacterPerson;

public interface CharacterMapper {

    CharacterDto toDto(CharacterPerson character);

    CharacterPerson toModel(CharacterRequestDto requestDto);
}
