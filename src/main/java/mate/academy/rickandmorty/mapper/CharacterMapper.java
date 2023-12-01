package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    CharacterResponseDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    Character toModel(ApiCharacterDto requestDto);

    @Named("genderToString")
    default String genderToString(Gender gender) {
        return gender.toString();
    }

    @Named("stringToGender")
    default Gender stringToGender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }

    @Named("statusToString")
    default String statusToString(Status status) {
        return status.toString();
    }

    @Named("stringToStatus")
    default Status stringToStatus(String status) {
        return Status.valueOf(status.toUpperCase());
    }
}
