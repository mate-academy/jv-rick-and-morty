package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Character responseDtoToCharacter(CharacterResponseDto responseDto) {
        Character character = new Character();
        character.setExternalId(responseDto.getId());
        character.setName(responseDto.getName());
        character.setGender(responseDto.getGender());
        character.setStatus(responseDto.getStatus());
        return character;
    }
}
