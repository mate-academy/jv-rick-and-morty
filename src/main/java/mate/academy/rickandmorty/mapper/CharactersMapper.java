package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ApiCharactersDto;
import mate.academy.rickandmorty.dto.internal.CharactersDto;
import mate.academy.rickandmorty.dto.internal.CreateCharactersDto;
import mate.academy.rickandmorty.model.Characters;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharactersMapper {
    CreateCharactersDto apiCharactersToDto(ApiCharactersDto requestDto);

    Characters toModel(CreateCharactersDto requestDto);

    CharactersDto toDto(Characters characters);

    List<CharactersDto> listToDto(List<Characters> characters);
}
