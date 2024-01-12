package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface CharacterMapper {
    CharacterRequestDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "id", ignore = true)
    Character toModel(CharacterResponseDto responseDto);
}
