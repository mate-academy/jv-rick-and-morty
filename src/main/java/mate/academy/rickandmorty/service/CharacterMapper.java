package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "gender", source = "gender")
    Character toCharacter(CharacterResponseDto dto);
}