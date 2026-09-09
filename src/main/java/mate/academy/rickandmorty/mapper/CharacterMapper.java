package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public Character toModel(RickAndMortyCharacterDto dto) {
        return Character.builder()
                .externalId(dto.id().toString())
                .name(dto.name())
                .status(dto.status())
                .gender(dto.gender())
                .build();
    }

    public CharacterResponseDto toDto(Character character) {
        return new CharacterResponseDto(
                character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender()
        );
    }
}
