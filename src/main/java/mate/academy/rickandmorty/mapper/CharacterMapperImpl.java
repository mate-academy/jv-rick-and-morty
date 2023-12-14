package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterDto toDto(Character character) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setId(character.getId());
        characterDto.setGender(characterDto.getGender());
        characterDto.setExternalId(character.getExternalId());
        return characterDto;
    }

    @Override
    public Character toModel(CreateCharacterRequestDto requestDto) {
        Character character = new Character();
        character.setExternalId(requestDto.getId());
        character.setGender(requestDto.getGender());
        character.setName(requestDto.getName());
        character.setStatus(requestDto.getStatus());
        return character;
    }
}
