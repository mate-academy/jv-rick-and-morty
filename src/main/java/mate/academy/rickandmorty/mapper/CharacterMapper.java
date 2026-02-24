package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", ignore = true)
    Character toModel(CharacterDto characterDto);
}
