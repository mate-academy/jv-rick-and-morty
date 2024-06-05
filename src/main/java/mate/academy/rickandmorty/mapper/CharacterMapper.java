package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.entity.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toEntity(CharacterDto characterDto);

    List<CharacterDto> toDtoList(List<Character> characters);

    List<Character> toEntityList(List<CharacterDto> characterDtos);
}
