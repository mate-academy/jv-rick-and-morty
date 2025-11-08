package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterRM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(target = "id", source = "dbId")
    @Mapping(target = "externalId", source = "externalId")
    CharacterDto toDto(CharacterRM character);

    @Mapping(target = "dbId", ignore = true)
    @Mapping(target = "externalId", source = "externalId")
    CharacterRM toEntity(CharacterDto dto);
}
