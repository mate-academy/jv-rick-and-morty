package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto toDto(CharacterEntity character);

    CharacterEntity toCharacter(CharacterInfoDto characterInfoDto);
}
