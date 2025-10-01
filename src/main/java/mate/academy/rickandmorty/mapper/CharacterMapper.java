package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ResponseCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {LocationMapper.class, EpisodeMapper.class})
public interface CharacterMapper {

    @Mapping(source = "image", target = "imageUrl")
    @Mapping(source = "episode", target = "episodes")
    @Mapping(source = "id", target = "externalId")
    Character toModel(ResponseCharacterDto characterDto);

    CharacterDto toDto(Character character);

}
