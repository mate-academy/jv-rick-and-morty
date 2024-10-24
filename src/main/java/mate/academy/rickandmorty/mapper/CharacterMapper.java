package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.mapper.MapperConfig;
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toModel(CreateCharacterDto createCharacterDto);

    CharacterDto toDto(Character character);

    List<CharacterDto> toDto(List<Character> characters);
}
