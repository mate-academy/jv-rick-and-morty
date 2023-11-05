package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfiguration;
import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface CharacterMapper {
    CharacterResponseDto toDto(Character character);

    Character toModel(CharacterApiDto characterApiDto);
}
