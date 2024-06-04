package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    List<CharacterDto> toCharacterDtoList(List<Character> characterList);

    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterResponseDto characterResponseDto);

    @Mapping(source = "id", target = "externalId")
    List<Character> toModelList(List<CharacterResponseDto> characterResponseDto);
}
