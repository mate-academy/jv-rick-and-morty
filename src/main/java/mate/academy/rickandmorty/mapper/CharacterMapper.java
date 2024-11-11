package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseInfoDto;
import mate.academy.rickandmorty.dto.internal.ResponceCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    ResponceCharacterDto toDto(Character character);

    Character toEntity(CharacterDto dto);

    ResponceCharacterDto fromInfoToResponse(CharacterResponseInfoDto dto);
}