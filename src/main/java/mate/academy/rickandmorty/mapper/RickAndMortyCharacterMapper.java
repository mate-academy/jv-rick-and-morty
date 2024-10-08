package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.configuration.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RickAndMortyCharacterMapper {
    @Mapping(source = "id", target = "externalId")
    @Mapping(source = "characterOriginDto", target = "origin")
    @Mapping(source = "characterLocationDto", target = "location")
    RickAndMortyCharacter toModel(CharacterResultDto resultDto);

    CharacterWikiDto toDto(RickAndMortyCharacter character);
}
