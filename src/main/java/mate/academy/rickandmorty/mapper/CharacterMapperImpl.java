package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterEntity toModel(ExternalCharacterDto externalCharacterDto) {
        if (externalCharacterDto == null) {
            return null;
        }
        CharacterEntity character = new CharacterEntity();
        character.setExternalId(String.valueOf(externalCharacterDto.getId()));
        character.setName(externalCharacterDto.getName());
        character.setStatus(externalCharacterDto.getStatus());
        character.setGender(externalCharacterDto.getGender());
        return character;
    }
}
