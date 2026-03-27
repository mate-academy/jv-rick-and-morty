package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;

public interface CharacterMapper {

    CharacterEntity toModel(ExternalCharacterDto externalCharacterDto);

}
