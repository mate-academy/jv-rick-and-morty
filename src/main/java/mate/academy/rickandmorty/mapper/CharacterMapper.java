package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.dto.api.ApiCharacterResponseDto;
import mate.academy.rickandmorty.model.dto.local.CharacterDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    Character toCharacter(ApiCharacterResponseDto responseDto);

    CharacterDto toDto(Character character);
}
