package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toEntity(CharacterRequestDto dto);

    CharacterResponseDto toDto(Character character);
}
