package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toModel(CharacterResponseDto characterResponseDto);

    OutputCharacterResponseDto toDto(Character character);
}
