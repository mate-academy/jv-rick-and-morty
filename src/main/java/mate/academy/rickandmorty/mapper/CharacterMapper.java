package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CreateCharacterRequestDto characterRequestDto);
}
