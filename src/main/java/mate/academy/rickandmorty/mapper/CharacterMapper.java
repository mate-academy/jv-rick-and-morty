package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    Character toModel(ExternalCharacterDto externalCharacterDto);

    InternalCharacterDto toDto(Character character);

    List<Character> toDtoCharacterList(List<ExternalCharacterDto> externalCharacterDtoList);

    List<InternalCharacterDto> toDtoInternalCharacterList(List<Character> characters);
}
