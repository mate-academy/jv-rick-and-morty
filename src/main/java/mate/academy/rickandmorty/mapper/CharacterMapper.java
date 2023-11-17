package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfiguration;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface CharacterMapper {

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", ignore = true)
    Character toModel(ExternalCharacterDto externalCharacterDto);

    ResponseCharacterDto toDto(Character character);
}
