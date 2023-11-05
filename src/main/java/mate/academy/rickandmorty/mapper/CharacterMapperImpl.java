package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Service;

@Service
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public Character toCharacter(CharacterDto characterDto) {
        Character character = new Character();
        character.setExternalId(characterDto.getId());
        character.setName(characterDto.getName());
        character.setStatus(characterDto.getStatus());
        character.setGender(characterDto.getStatus());
        return character;
    }

    @Override
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
