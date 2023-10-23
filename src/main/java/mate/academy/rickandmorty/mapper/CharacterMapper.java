package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    List<Character> toModel(List<CharacterDto> characterDto);

    @AfterMapping
    default void setExternalId(@MappingTarget Character character, CharacterDto characterDto) {
        character.setExternalId(characterDto.getId());
    }

    CharacterResponseDto toDto(Character character);

    @AfterMapping
    default void setId(@MappingTarget CharacterResponseDto responseDto, Character character) {
        responseDto.setId(character.getId());
    }
}
