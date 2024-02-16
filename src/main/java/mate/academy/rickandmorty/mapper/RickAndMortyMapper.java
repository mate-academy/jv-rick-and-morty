package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyMapper {
    @Mapping(source = "id", target = "externalId")
    RickAndMortyCharacter toModel(CharacterResultDto characterDto);

    RickAndMortyCharacterDto toDto(RickAndMortyCharacter characterFromDb);
}
