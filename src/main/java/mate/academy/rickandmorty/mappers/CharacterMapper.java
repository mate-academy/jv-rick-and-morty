package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.config.MapperConfiguration;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    Character toEntity(RandMResponseDto dto);

    CharacterOfRickyAndMortyDto toDto(Character character);

}
