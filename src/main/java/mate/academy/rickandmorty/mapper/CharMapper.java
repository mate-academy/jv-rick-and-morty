package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharMapper {
    CharacterModel toModel(CharacterDto characterDto);

    CharacterDto toDto(CharacterModel characterModel);
}
