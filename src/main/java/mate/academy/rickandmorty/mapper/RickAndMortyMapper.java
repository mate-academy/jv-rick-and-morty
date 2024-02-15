package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyMapper {

    RickAndMortyCharacter toModel(CharacterResultDto characterDto);

    RickAndMortyCharacterDto toDto(RickAndMortyCharacter characterFromDb);
}
