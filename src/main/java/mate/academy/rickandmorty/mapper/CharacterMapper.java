package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDto toDto(RickAndMortyCharacter character);
}
