package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterExternalDto;
import mate.academy.rickandmorty.model.RikAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    CharacterDto toDto(RikAndMortyCharacter character);

    @Mapping(target = "externalId", expression = "java(setExternalId(characterResponseDto))")
    RikAndMortyCharacter toEntity(CharacterExternalDto characterResponseDto);

    default Long setExternalId(CharacterExternalDto characterExternalDto) {
        return characterExternalDto.getId();
    }
}
