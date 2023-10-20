package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "externalId")
    Character toEntity(CharacterResponseDto responseDto);

    CharacterDto toDto(Character character);

    List<Character> toEntityList(List<CharacterResponseDto> responseDtoList);

    List<CharacterDto> toDtoList(List<Character> characters);
}
