package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.dto.internal.InternalCharResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    default Character toEntity(InternalCharResponseDto responseDto) {
        Character character = new Character();
        character.setExternalId(responseDto.id());
        character.setName(responseDto.name());
        character.setStatus(responseDto.status());
        character.setGender(responseDto.gender());
        return character;
    }

    default ExternalCharResponseDto toDto(Character character) {
        return new ExternalCharResponseDto(
                character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender()
        );
    }
}
