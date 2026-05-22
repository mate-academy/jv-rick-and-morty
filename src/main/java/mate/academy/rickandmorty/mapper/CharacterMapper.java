package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    InternalCharacterDto toDto(Character character);

    Character toModel(ExternalCharacterDto dto);
}
