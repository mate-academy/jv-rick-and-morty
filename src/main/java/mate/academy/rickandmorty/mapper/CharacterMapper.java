package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.dto.character.CharacterResponseDto;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    CharacterFromRickAndMorty toCharacterModel(CharacterResponseDto responseDto);

    CharacterDto toDto(CharacterFromRickAndMorty characterFromRickAndMorty);
}
