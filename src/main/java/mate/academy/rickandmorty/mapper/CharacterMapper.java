package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterApiDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Named("toDto")
    CharacterDto toDto(Character character);

    @IterableMapping(qualifiedByName = "toDto")
    List<CharacterDto> toDtos(List<Character> characters);

    Character toModel(CharacterDto characterDto);

    @Named("toModel")
    @Mapping(target = "externalId", source = "id")
    Character toModel(CharacterApiDto apiDto);
    
    @IterableMapping(qualifiedByName = "toModel")
    List<Character> toModels(List<CharacterApiDto> apiDtoList);
}
