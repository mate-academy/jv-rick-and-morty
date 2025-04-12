package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.CharacterShortInfoDto;
import mate.academy.rickandmorty.model.CharacterModel;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterShortInfoMapper {
    CharacterShortInfoDto toDto(CharacterModel characterModel);
}
