package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.ClientCharacterDto;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", ignore = true)
    Character toModel(ClientCharacterDto clientCharacterDto);

    ResponseCharacterDto toDto(Character character);
}
