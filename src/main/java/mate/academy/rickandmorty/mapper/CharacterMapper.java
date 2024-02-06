package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.model.RickAndMortyChars;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterInternalDto toCharacterDto(RickAndMortyChars rickAndMortyChars);

    @Mapping(source = "id", target = "externalId")
    RickAndMortyChars toCharacterModel(CharacterResponseDto characterDto);
}
