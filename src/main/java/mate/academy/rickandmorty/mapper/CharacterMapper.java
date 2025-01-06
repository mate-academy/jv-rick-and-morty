package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character fromExternalToEntity(ExternalCharacterDto externalCharacterDto);

    InternalCharacterDto fromEntityToInternalDto(Character character);

    InternalCharacterDto fromEntityToInternal(Character character);
}
