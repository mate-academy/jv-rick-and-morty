package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapStructConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseResultsDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface CharacterMapper {

    CharacterDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toModelFromApiResponse(CharacterResponseResultsDto dto);
}
