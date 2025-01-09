package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.LocationInputDataDto;
import mate.academy.rickandmorty.dto.internal.LocationCreateRequestDto;
import mate.academy.rickandmorty.dto.internal.LocationDto;
import mate.academy.rickandmorty.model.Location;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LocationMapper {
    LocationDto toDto(Location location);

    Location toModel(LocationCreateRequestDto locationCreateRequestDto);

    LocationCreateRequestDto fromExternalDataInputToCreationRequest(
            LocationInputDataDto locationInputDataDto);
}
