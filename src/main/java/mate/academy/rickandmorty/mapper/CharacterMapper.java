package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(target = "status", expression = "java(getStatusValue(dto.getStatus()))")
    @Mapping(target = "gender", expression = "java(getGenderValue(dto.getGender()))")
    Character toEntity(CharacterRequestDto dto);

    default Gender getGenderValue(String name) {
        return Gender.getFromString(name);
    }

    default Status getStatusValue(String name) {
        return Status.getFromString(name);
    }

    CharacterResponseDto toDto(Character character);
}
