package mate.academy.rickandmorty.mapper.internal;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterWikiDto;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SeriesCharacterWikiMapper {

    SeriesCharacterWikiDto toCharacterWikiDto(SeriesCharacter seriesCharacter);
}
