package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "id", ignore = true)
    CharacterEntity mapToEntity(ApiCharacterDto apiCharacterDto);

    ResponseCharacterDto mapToResponseDto(CharacterEntity characterEntity);
}
