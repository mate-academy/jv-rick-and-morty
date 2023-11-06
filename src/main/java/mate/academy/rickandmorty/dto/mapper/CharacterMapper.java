package mate.academy.rickandmorty.dto.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(target = "id", source = "externalId")
    CharacterDto characterToCharacterDto(Character character);

    Character characterDtoToCharacter(CharacterDto characterDto);

    List<CharacterDto> map(List<Character> characters);

}
