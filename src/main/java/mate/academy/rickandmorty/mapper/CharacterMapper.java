package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    List<Character> toCharacterModels(List<CharacterResultsDto> characterResultsDto);

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", ignore = true)
    Character toCharacterModel(CharacterResultsDto characterResultsDto);
}
