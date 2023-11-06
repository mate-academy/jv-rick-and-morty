package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.model.external.CharacterResultDto;
import mate.academy.rickandmorty.model.internal.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toModel(CharacterResultDto characterResultDto);
}
