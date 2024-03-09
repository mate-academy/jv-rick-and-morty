package mate.academy.rickandmorty.mapper.impl;

import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyMapperImpl implements RickAndMortyMapper {

    @Override
    public Character toModel(ExternalCharacterDto responseDto) {
        Character character = new Character();
        character.setName(responseDto.getName());
        character.setGender(responseDto.getGender());
        character.setExternalId(responseDto.getExternalId());
        character.setStatus(responseDto.getStatus());
        return character;
    }

    @Override
    public CharacterDto toDto(Character character) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setExternalId(character.getExternalId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setGender(character.getGender());
        return characterDto;
    }
}
