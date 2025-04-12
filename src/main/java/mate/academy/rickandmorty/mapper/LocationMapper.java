package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.LocationDto;
import mate.academy.rickandmorty.model.Location;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LocationMapper {
    Location toModel(LocationDto locationDto);
}
