package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.model.Personage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonageMapper {
    PersonageDto intoDto(Personage personage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", source = "id")
    Personage intoModel(CreatePersonageRequestDto requestDto);
}
