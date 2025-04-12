package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterModelDto;
import mate.academy.rickandmorty.dto.external.LocationDto;
import mate.academy.rickandmorty.dto.external.OriginDto;
import mate.academy.rickandmorty.model.CharacterModel;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {LocationMapper.class, OriginMapper.class})
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    CharacterModel toModel(CharacterModelDto characterModelDto);

    @BeforeMapping
    private CharacterModelDto setOriginDto(CharacterModelDto characterModelDto) {
        OriginDto originDto = characterModelDto.getOrigin() != null
                ? new OriginDto(characterModelDto.getOrigin().name(),
                characterModelDto.getOrigin().url())
                : null;
        characterModelDto.setOrigin(originDto);
        return characterModelDto;
    }

    @BeforeMapping
    private CharacterModelDto setLocationDto(CharacterModelDto characterModelDto) {
        LocationDto locationDto = characterModelDto.getLocation() != null
                ? new LocationDto(characterModelDto.getLocation().name(),
                characterModelDto.getLocation().url())
                : null;
        characterModelDto.setLocation(locationDto);
        return characterModelDto;
    }
}
