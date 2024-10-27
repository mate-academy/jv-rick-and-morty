package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    Character toModel(CharacterDto characterDto);

    @Mapping(source = "id", target = "externalId")
    List<Character> toModelList(List<CharacterDto> characterDtoList);

    CharacterDto toDto(Character character);

    List<CharacterDto> toDtoList(List<Character> characterList);
}
