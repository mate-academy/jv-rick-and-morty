package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface MovieCharacterMapper {
    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "status", source = "status", qualifiedByName = "toUpperCaseStatus")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "toUpperCaseGender")
    @Mapping(target = "id", ignore = true)
    MovieCharacter toModel(ApiCharacterDto apiCharacterDto);

    MovieCharacterResponseDto toResponseDto(MovieCharacter movieCharacter);

    @Named("toUpperCaseStatus")
    default Status toUpperCaseStatus(String status) {
        return Status.valueOf(status.toUpperCase());
    }

    @Named("toUpperCaseGender")
    default Gender toUpperCaseGender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
}
