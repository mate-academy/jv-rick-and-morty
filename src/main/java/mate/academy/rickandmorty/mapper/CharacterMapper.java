package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapstructConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterDto dto);

    CharacterResponseDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character updateModel(@MappingTarget Character character, CharacterDto characterDto);
}

