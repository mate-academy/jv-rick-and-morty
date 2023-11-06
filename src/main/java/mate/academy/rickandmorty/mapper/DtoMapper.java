package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface DtoMapper {
    @Mapping(target = "externalId", source = "characterResponseDto.id")
    Character responseDtoToCharacter(CharacterResponseDto characterResponseDto);
}
