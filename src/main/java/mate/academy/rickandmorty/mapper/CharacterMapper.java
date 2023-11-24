package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ApiCharacterResultDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    Character toModel(ApiCharacterResultDataDto resultDataDto);

    CharacterResponseDto toDto(Character entity);
}
