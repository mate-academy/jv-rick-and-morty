package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterApiDto toDto(Character character) {
        CharacterApiDto dto = new CharacterApiDto();
        dto.setExternalId(character.getId());
        dto.setName(character.getName());
        dto.setGender(character.getGender());
        dto.setGender(character.getGender());

        return dto;
    }

    @Override
    public Character toModel(CharacterApiDto dto) {
        Character character = new Character();
        character.setId(dto.getExternalId());
        character.setExternalId(dto.getExternalId());
        character.setName(dto.getName());
        character.setGender(dto.getGender());
        character.setStatus(dto.getStatus());

        return character;
    }
}
