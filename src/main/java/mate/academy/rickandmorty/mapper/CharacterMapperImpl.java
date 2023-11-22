package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public Character toModel(CharacterInfoDto dto) {
        Character character = new Character();
        character.setName(dto.getName());
        character.setStatus(dto.getStatus());
        character.setGender(dto.getGender());
        character.setExternalId(String.valueOf(dto.getId()));
        return character;
    }

    @Override
    public CharacterResponseDto toDto(Character character) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(character.getId());
        dto.setExternalId(character.getExternalId());
        dto.setName(character.getName());
        dto.setGender(character.getGender());
        dto.setStatus(character.getStatus());
        return dto;
    }
}
