package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toCharacterDto(Character character);

    Character toCharacterModel(CharacterDto characterDto);

    @Mapping(source = "id", target = "externalId")
    Character toCharacterModel(CharacterResponseInfoDto characterDto);
}
