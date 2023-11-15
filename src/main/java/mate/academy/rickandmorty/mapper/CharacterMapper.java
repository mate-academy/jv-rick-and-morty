package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterMapper {
    CharacterDto toDto(Character character);

    List<CharacterDto> toDtos(List<Character> characters);

    Character toModel(CharacterResponseDto responseDto);

    List<Character> toModels(List<CharacterResponseDto> responseDtos);
}
