package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.dto.RickMortyDtoResponse;
import mate.academy.rickandmorty.mapperconfig.MapperConfig;
import mate.academy.rickandmorty.models.RickMorty;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RickMortyMapper {
    RickMorty toModel(RickMortyDtoResponse rickMortyDtoRequest);

}
