package mate.academy.rickandmorty.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickAndMortyResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @JsonIgnoreProperties(ignoreUnknown = true)
    CharacterDto toDto(Character character);

    @Mapping(target = "externalId", source = "id")
    Character toModel(RickAndMortyResultDto rickAndMortyResultDto);
}
