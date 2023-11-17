package mate.academy.rickandmorty.config;

import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    Character mapToCharacter(CharacterExternalDto dto);

}
