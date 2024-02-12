package mate.academy.rickandmorty.mappper;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperToEntity implements MapperToEntity<CharacterResponseDto, Character> {
    @Override
    public Character toEntity(CharacterResponseDto dto) {
        Character character = new Character();
        character.setExternalId(dto.id());
        character.setName(dto.name());
        character.setGender(dto.gender());
        character.setStatus(dto.status());
        return character;
    }
}
