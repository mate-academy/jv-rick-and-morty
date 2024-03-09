package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface RickAndMortyMapper {

    Character toModel(ExternalCharacterDto responseDto);

    CharacterDto toDto(Character character);
}
