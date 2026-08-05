package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public Character toModel(ExternalCharacterDto dto) {
        Character character = new Character();
        character.setExternalId(String.valueOf(dto.id()));
        character.setName(dto.name());
        character.setStatus(dto.status());
        character.setGender(dto.gender());
        return character;
    }

    public CharacterDto toDto(Character character) {
        return new CharacterDto(
                character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender()
        );
    }
}

