package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    List<CharacterDto> toDtoList(List<Character> characters);

    Character toModel(CharacterDto characterDto);

    Character toModel(CharacterResponseDto characterResponseDto);

    List<Character> toModelList(List<CharacterResponseDto> characterResponseDtos);
}
