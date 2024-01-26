package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    CharacterDto toDto(Character character);

    Character toModel(CharacterFromExternalApiDto characterResponse);
}
