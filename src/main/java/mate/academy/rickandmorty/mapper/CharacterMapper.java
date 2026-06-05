package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterResponseDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterDto characterDto);
}
