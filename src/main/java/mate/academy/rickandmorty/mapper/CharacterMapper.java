package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public Character toModel(ExternalCharacterDto dto) {
        Character character = new Character();
        character.setExternalId(String.valueOf(dto.getId()));
        character.setName(dto.getName());
        character.setStatus(dto.getStatus());
        character.setGender(dto.getGender());
        return character;
    }

    public CharacterDto toDto(Character character) {
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setExternalId(character.getExternalId());
        dto.setName(character.getName());
        dto.setStatus(character.getStatus());
        dto.setGender(character.getGender());
        return dto;
    }
}

