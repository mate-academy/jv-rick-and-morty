package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ResponseSeriesCharacterDataDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterDto;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SeriesCharacterMapper {
    @Mapping(target = "externalId", source = "id")
    SeriesCharacter toModel(ResponseSeriesCharacterDataDto seriesCharacterDataDto);

    SeriesCharacterDto toDto(SeriesCharacter seriesCharacter);
}
