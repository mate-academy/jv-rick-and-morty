package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.dto.character.CreateCharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.type.GenderType;
import mate.academy.rickandmorty.model.type.StatusType;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CreateCharacterRequestDto createCharacterRequestDto);

    default StatusType mapStatus(String value) {
        return StatusType.fromString(value);
    }

    default GenderType mapGender(String value) {
        return GenderType.fromString(value);
    }
}
