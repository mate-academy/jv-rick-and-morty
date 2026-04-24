package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.entity.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public CharacterResponseDto toDto(Character character) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(character.getId());
        characterResponseDto.setExternalId(character.getExternalId());
        characterResponseDto.setName(character.getName());
        characterResponseDto.setStatus(character.getStatus());
        characterResponseDto.setGender(character.getGender());
        return characterResponseDto;
    }

    public Character toEntity(ApiCharacterDto apiCharacterDto) {
        Character character = new Character();
        character.setExternalId(String.valueOf(apiCharacterDto.getId()));
        character.setName(apiCharacterDto.getName());
        character.setStatus(apiCharacterDto.getStatus());
        character.setGender(apiCharacterDto.getGender());
        return character;
    }
}
