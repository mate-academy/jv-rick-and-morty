package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.ResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface CharacterMapper {
    CharacterDto toCharacterDto(Character character);

    @Mappings({
            @Mapping(target = "externalId", source = "id"),
            @Mapping(target = "id", ignore = true) // DB auto-generated ID
    })
    Character toEntity(ExternalCharacterDto dto);

    @Mappings({
            @Mapping(target = "externalId", source = "externalId"),
            @Mapping(target = "id", source = "id")
    })
    ResponseDto toResponseDto(Character character);
}
