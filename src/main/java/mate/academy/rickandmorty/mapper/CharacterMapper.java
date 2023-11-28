package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CreateCharacterDto apiCharacterToDto(ApiCharacterDto apiCharacterDto);

    Character toModel(CreateCharacterDto createCharacterDto);

    CharacterDto toDto(Character characters);

    List<CharacterDto> listToDto(List<Character> characters);
}
