package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", source = "id")
    RickAndMortyCharacter toModel(RickAndMortyCharacterDto characterDto);
}
