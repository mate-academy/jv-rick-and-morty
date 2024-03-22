package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public CharacterResponseDto toResponseDto(Character character) {
        CharacterResponseDto characterDto = new CharacterResponseDto();
        characterDto.setId(character.getId());
        characterDto.setExternalId(character.getExternalId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setGender(character.getGender());
        return characterDto;
    }

    public Character toModel(ApiCharacterDto dtoForDb) {
        Character character = new Character();
        character.setExternalId(dtoForDb.getId());
        character.setName(dtoForDb.getName());
        character.setGender(Gender.valueOf(dtoForDb.getGender().toUpperCase()));
        character.setStatus(Status.valueOf(dtoForDb.getStatus().toUpperCase()));
        return character;
    }
}
