package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RequestResultsDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterResponseDto toResponseDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toEntity(RequestResultsDto dto);
}
