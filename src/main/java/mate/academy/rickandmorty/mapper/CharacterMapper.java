package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toEntity(CharacterResponseDto characterResponseDto);

    CharacterResponseDto toDto(Character character);

    List<Character> toSetOfEntity(List<CharacterResponseDto> characterResponseDto);

}
