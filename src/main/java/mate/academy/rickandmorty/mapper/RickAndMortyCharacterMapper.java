package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyCharacterMapper {

    RickAndMortyCharacterDto toDto(RickAndMortyCharacter rickAndMortyCharacter);

    @Mapping(source = "id", target = "externalId")
    RickAndMortyCharacter toModel(RickAndMortyCharacterDto dto);
}
