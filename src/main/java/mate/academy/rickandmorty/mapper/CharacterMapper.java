package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacter;
import mate.academy.rickandmorty.dto.internal.CharacterDataDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "characterDto.id")
    Character toEntity(CharacterDataDto characterDto);

    ExternalCharacter toDto(Character character);
}
