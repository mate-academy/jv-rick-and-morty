package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ApiMovieCharacterDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterDto;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface MovieCharacterMapper {
    @Mapping(source = "id", target = "externalId")
    MovieCharacter toModel(ApiMovieCharacterDto apiMovieCharacterDto);

    MovieCharacterDto toDto(MovieCharacter movieCharacter);
}
