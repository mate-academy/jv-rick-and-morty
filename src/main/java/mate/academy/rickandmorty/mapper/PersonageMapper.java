package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.PersonageDataDto;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.model.Personage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface PersonageMapper {
    PersonageDto toDto(Personage personage);

    Personage toModel(CreatePersonageRequestDto requestDto);

    @Mapping(target = "externalId", source = "id")
    Personage toModelFromMeteData(PersonageDataDto dataDto);

    Personage updatePersonageFromDto(
            CreatePersonageRequestDto requestDto,
            @MappingTarget Personage personage);
}
