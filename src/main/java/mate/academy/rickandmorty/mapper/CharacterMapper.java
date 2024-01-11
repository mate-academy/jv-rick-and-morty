package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ExternalCharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    Character toCharacter(ExternalCharacterResponseDto externalCharacterResponseDto);

    InternalCharacterDto toDto(Character character);
}
