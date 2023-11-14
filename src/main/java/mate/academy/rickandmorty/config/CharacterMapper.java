package mate.academy.rickandmorty.config;

import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public Character mapToCharacter(CharacterExternalDto dto) {
        Character character = new Character();
        character.setExternalId("" + dto.getId());
        character.setName(dto.getName());
        character.setStatus(dto.getStatus());
        character.setGender(dto.getGender());
        return character;
    }
}
