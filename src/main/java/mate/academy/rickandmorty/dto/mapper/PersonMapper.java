package mate.academy.rickandmorty.dto.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.PersonalityDto;
import mate.academy.rickandmorty.model.Personality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface PersonMapper {
    PersonalityDto toDto(Personality personality);

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "deleted", ignore = true)
    Personality characterDtoToPersonality(CharacterDto characterDto);

    @Mapping(target = "status", source = "status", qualifiedByName = "toProperCase")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "toProperCase")
    PersonalityDto toPersonalityDtoFromEntity(Personality personality);

    @Named("toProperCase")
    default String toProperCase(String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("UNKNOWN")) {
            return "unknown";
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }
}
