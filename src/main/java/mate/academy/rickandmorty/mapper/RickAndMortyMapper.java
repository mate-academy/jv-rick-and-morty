package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.model.RickAndMorty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyMapper {

    @Mapping(target = "externalId", source = "id")
    RickAndMorty toModel(ExternalCharacterDto characterDto);

    CharacterDto toDto(RickAndMorty rickAndMorty);
}
