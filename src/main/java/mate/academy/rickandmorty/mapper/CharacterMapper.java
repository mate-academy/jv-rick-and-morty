package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterResultDataDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    MovieCharacter toModel(CharacterResultDataDto resultDataDto);

    MovieCharacterResponseDto toDto(MovieCharacter entity);
}
