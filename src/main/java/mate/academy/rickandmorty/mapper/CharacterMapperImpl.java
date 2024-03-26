package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.model.CharacterPerson;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterDto toDto(CharacterPerson character) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setExternalId(character.getExternalId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setGender(character.getGender());
        return characterDto;
    }

    @Override
    public CharacterPerson toModel(CharacterRequestDto requestDto) {
        CharacterPerson character = new CharacterPerson();
        character.setExternalId(requestDto.getExternalId());
        character.setGender(requestDto.getGender());
        character.setName(requestDto.getName());
        character.setStatus(requestDto.getStatus());
        return character;
    }
}
