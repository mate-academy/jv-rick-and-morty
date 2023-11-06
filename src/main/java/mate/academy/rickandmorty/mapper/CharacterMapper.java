package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultsResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    SeriesCharacter toModel(CharacterResultsResponseDto characterDto);

    CharacterDto toInternalDto(SeriesCharacter seriesCharacter);
}
