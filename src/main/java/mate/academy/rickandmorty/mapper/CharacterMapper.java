package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RnMResponseResultDto;
import mate.academy.rickandmorty.model.RnMCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    RnMCharacter toModel(RnMResponseResultDto responseDto);
}
