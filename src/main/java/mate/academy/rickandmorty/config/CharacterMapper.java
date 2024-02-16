package mate.academy.rickandmorty.config;

import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    CharacterEntity mapToCharacter(CharacterResultsDto dto);
}
