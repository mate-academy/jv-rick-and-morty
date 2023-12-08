package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterResponseDto;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    SeriesCharacter toModel(CharacterResultDto resultDto);

    SeriesCharacterResponseDto toDto(SeriesCharacter seriesCharacter);
}
