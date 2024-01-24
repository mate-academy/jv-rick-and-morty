package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto toDto(Character character);

    @Mapping(target = "externalId", source = "id")
    Character toModel(CharacterResultDto responseDataDto);
}
