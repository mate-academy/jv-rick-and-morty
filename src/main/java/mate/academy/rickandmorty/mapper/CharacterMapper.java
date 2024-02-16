package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    Character toModel(CharacterExternalResponseDto characterExternalResponseDto);

    CharacterInternalResponseDto toDto(Character character);
}
