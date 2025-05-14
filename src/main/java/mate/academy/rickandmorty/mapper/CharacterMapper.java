package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    ExternalCharacterDto toDto(Character character);

    Character toEntity(ExternalCharacterDto externalCharacterDto);

    List<ExternalCharacterDto> toDtoList(List<Character> characters);

    List<Character> toEntityList(List<ExternalCharacterDto> externalCharacterDtos);
}
