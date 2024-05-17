package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterResponseDto toDto(Character character);

    List<CharacterResponseDto> toDto(List<Character> characters);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterApiResponseDto responseDto);

    List<Character> toModel(List<CharacterApiResponseDto> responseDto);
}
