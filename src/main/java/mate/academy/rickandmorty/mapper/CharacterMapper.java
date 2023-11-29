package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDtoResult;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", ignore = true)
    CharacterDto toInternalDto(ExternalCharacterDtoResult getAllCharactersFromExternalDataBase);
    CharacterDto toDto(Character character);
    Character toModel(CharacterDto characterDto);
}
