package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterRespondDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(ignore = true, target = "id")
    Character toModel(CharacterDto character);

    @Mapping(source = "id", target = "externalId")
    CharacterRespondDto toDto(Character character);
}
