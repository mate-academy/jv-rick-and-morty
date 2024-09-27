package mate.academy.rickandmorty.mapping;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.models.Character;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    Character convertFromResponseDto(CharacterResponseDto characterResponseDto);

    CharacterDto convertToDto(Character character);

    void updateCharacterFromResponseDto(CharacterResponseDto responseDto,
                                        @MappingTarget Character character);
}
