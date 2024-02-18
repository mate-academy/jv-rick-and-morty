package mate.academy.rickandmorty.mapper;

import config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    MovieCharacter toEntity(ApiCharacterDto dto);

    CharacterResponseDto toResponseDto(MovieCharacter movieCharacter);

}
