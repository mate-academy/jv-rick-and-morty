package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterRequestDto toDto(Character character);

    @Mappings({
            @Mapping(target = "externalId", source = "id"),
            @Mapping(target = "id", ignore = true)
    })
    Character toModel(CharacterResponseDto characterResponseDto);
}
