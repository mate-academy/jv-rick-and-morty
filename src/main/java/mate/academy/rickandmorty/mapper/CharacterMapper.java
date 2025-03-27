package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public Character toEntity(CharacterApiResponseDto dto) {
        Character character = new Character();
        character.setExternalId(dto.getId().toString());
        character.setName(dto.getName());
        character.setStatus(dto.getStatus());
        character.setGender(dto.getGender());
        return character;
    }

    public CharacterResponseDto toResponseDto(Character character) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(character.getId());
        characterResponseDto.setExternalId(character.getExternalId());
        characterResponseDto.setName(character.getName());
        characterResponseDto.setStatus(character.getStatus());
        characterResponseDto.setGender(character.getGender());
        return characterResponseDto;
    }
}
