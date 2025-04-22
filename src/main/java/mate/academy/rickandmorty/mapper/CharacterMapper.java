package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRecordDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = mate.academy.rickandmorty.config.MapperConfig.class)
public interface CharacterMapper {

    Character toModel(CharacterRecordDto recordDto);

    CharacterDto toDto(Character character);
}
