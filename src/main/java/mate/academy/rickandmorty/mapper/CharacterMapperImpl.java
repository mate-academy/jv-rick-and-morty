package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    @Override
    public CharacterDto toDto(Character character) {
        return new CharacterDto(character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender());
    }

    @Override
    public List<CharacterDto> toDtos(List<Character> characters) {
        return characters.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Character toModel(CharacterResponseDto responseDto) {
        Character character = new Character();
        character.setExternalId(String.valueOf(responseDto.id()));
        character.setName(responseDto.name());
        character.setStatus(responseDto.status());
        character.setGender(responseDto.gender());
        return character;
    }

    @Override
    public List<Character> toModels(List<CharacterResponseDto> responseDtos) {
        return responseDtos.stream()
                .map(this::toModel)
                .toList();
    }
}
