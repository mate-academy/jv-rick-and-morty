package spring.rickandmorty.mapper;

import org.mapstruct.Mapper;
import spring.rickandmorty.dto.ExternalCharacterDto;
import spring.rickandmorty.model.Character;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    Character toEntity(ExternalCharacterDto dto);
}
