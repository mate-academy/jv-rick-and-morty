package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.RickMortyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "id", source = "externalId")
    CharacterDto toDto(RickMortyCharacter character);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", source = "id")
    RickMortyCharacter toModel(CharacterInfoDto character);
}
