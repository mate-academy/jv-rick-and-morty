package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyMapper {
    RickAndMortyCharacter toModel(RickAndMortyCharacterDto characterDto);
}
