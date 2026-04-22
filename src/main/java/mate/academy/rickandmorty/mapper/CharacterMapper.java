package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapStructConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface CharacterMapper {

    CharacterWikiDto toWikiDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toModelFromApiResponse(CharacterResponseDto dto);
}
