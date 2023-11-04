package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ResultResponseDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MovieCharacterMapper {
    MovieCharacter toModel(ResultResponseDto resultResponseDto);

    MovieCharacterResponseDto toDto(MovieCharacter movieCharacter);
}
