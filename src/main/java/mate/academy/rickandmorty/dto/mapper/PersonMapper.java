package mate.academy.rickandmorty.dto.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.PersonalityDto;
import mate.academy.rickandmorty.model.Personality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonMapper {
    PersonalityDto toDto(Personality personality);

    @Mapping(target = "externalId", source = "id")
    Personality characterDtoToPersonality(CharacterDto characterDto);

    PersonalityDto toPersonalityDtoFromEntity(Personality personality);
}
