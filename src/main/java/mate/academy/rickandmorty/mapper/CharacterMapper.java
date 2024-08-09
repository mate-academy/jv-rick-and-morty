package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResultsDataDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterResultsDataDto resultsDataDto);

    List<Character> toModelList(List<CharacterResultsDataDto> resultsDataDtoList);

    @Mapping(source = "externalId", target = "id")
    CharacterDto toDto(Character character);

    List<CharacterDto> toListDto(List<Character> characters);
}
