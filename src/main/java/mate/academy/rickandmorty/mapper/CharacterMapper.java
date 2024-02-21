package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterFromApi;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(mate.academy.rickandmorty.model.Character character);

    @Mapping(target = "externalId", source = "id")
    Character toModel(CharacterFromApi characterFromApi);
}
