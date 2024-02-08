package mate.academy.rickandmorty.mapper.internal;

import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterDto toModel(Character character) {
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setExternalId(character.getExternalId());
        dto.setName(character.getName());
        dto.setGender(character.getGender());
        dto.setStatus(character.getStatus());
        return dto;
    }

    @Override
    public Character toDto(CharacterDto characterDto) {
        Character character = new Character();
        character.setExternalId(characterDto.getExternalId());
        character.setGender(characterDto.getGender());
        character.setName(characterDto.getName());
        character.setStatus(characterDto.getStatus());
        return character;
    }
}
