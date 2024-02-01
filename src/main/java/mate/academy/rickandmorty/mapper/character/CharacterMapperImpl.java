package mate.academy.rickandmorty.mapper.character;

import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.dto.character.CharacterFromApiDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {

    @Override
    public CharacterDto toDto(Character character) {
        if (character == null) {
            return null;
        }

        CharacterDto characterDto = new CharacterDto();

        characterDto.setId(character.getId());
        characterDto.setExternalId(character.getExternalId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setGender(character.getGender());

        return characterDto;
    }

    @Override
    public Character toModel(CharacterFromApiDto character) {
        if (character == null) {
            return null;
        }

        Character character1 = new Character();

        character1.setExternalId(character.getExternalId());
        character1.setName(character.getName());
        character1.setStatus(character.getStatus());
        character1.setGender(character.getGender());

        return character1;
    }
}
