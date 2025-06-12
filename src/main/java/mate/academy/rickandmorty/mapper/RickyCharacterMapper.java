package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickyCharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.RickyCharacterDto;
import mate.academy.rickandmorty.model.RickyCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RickyCharacterMapper {
    RickyCharacter toEntity(RickyCharacterResponseDataDto rickyCharacterResponseDataDto);

    RickyCharacterDto toDto(RickyCharacter rickyCharacter);
}
