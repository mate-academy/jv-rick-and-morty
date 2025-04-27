package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.dto.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.entity.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "externalId", source = "id")
    Character fromApiDto(RickAndMortyApiCharacterDto apiDto);

    @Mapping(target = "externalId", source = "id")
    CharactersDto fromApiToDto(RickAndMortyApiCharacterDto apiDto);

    Character toModel(CharactersDto responseDto);

    CharactersDto toDto(Character character);
}
