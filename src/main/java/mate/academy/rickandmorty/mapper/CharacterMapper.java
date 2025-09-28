package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    RickAndMortyCharacter toModel(CharacterDto characterDto);

    CharacterDto toDto(RickAndMortyCharacter rickAndMortyCharacter);
}
