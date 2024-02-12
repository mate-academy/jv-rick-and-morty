package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CharactersMapper {
    CharacterDto toDto(Character character);

    @Mapping(target = "id", ignore = true)
    Character toModel(CharacterRequestDto requestDto);

    @AfterMapping
    default void setExternalId(@MappingTarget Character character, CharacterRequestDto requestDto) {
        character.setExternalId(String.valueOf(requestDto.id()));
    }
}
