package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Импортируем MapStruct Mapping

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "id", ignore = true)
    RickAndMortyCharacter toModel(CharacterDto characterDto);

    CharacterDto toDto(RickAndMortyCharacter rickAndMortyCharacter);
}
