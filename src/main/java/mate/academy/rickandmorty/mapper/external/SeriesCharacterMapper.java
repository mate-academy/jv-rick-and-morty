package mate.academy.rickandmorty.mapper.external;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SeriesCharacterMapper {

    SeriesCharacterDto toSeriesCharacterDto(SeriesCharacter seriesCharacter);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", source = "id")
    SeriesCharacter toSeriesCharacter(SeriesCharacterDto seriesCharacterDto);
}
