package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    ResponseCharacterDto toInternalDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toModel(RickAndMortyApiCharacterDto externalDto);
}
