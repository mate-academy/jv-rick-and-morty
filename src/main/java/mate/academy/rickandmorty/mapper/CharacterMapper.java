package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterConversionDto;
import mate.academy.rickandmorty.model.CharacterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    CharacterModel toCharacterModels(CharacterDto characterDto);

    List<CharacterModel> toCharacterModels(List<CharacterDto> characterDtos);

    CharacterConversionDto toCharacterConversionDto(CharacterModel characterModel);

    List<CharacterConversionDto> toCharacterDtos(List<CharacterModel> characterModels);
}
