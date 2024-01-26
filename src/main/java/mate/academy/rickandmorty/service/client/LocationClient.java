package mate.academy.rickandmorty.service.client;

import java.io.IOException;
import java.util.List;
import mate.academy.rickandmorty.dto.external.location.LocationDto;
import mate.academy.rickandmorty.dto.external.location.LocationSearchParameters;
import mate.academy.rickandmorty.dto.external.location.LocationsDto;

public interface LocationClient {
    LocationsDto getLocations() throws IOException, InterruptedException;

    List<LocationDto> getLocationsByIds(Integer[] ids) throws IOException, InterruptedException;

    LocationsDto getLocationByParams(LocationSearchParameters locationSearchParameters)
            throws IOException, InterruptedException;
}
