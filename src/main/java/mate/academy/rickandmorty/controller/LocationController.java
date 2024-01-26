package mate.academy.rickandmorty.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.location.LocationDto;
import mate.academy.rickandmorty.dto.external.location.LocationSearchParameters;
import mate.academy.rickandmorty.dto.external.location.LocationsDto;
import mate.academy.rickandmorty.service.client.LocationClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final LocationClient locationClient;

    @GetMapping
    public LocationsDto getLocations() throws IOException, InterruptedException {
        return locationClient.getLocations();
    }

    @GetMapping("/{ids}")
    public List<LocationDto> getCharacterByIds(@PathVariable Integer... ids)
            throws IOException, InterruptedException {
        return locationClient.getLocationsByIds(ids);
    }

    @GetMapping("/search")
    public LocationsDto getCharactersByParams(LocationSearchParameters locationSearchParameters)
            throws IOException, InterruptedException {
        return locationClient.getLocationByParams(locationSearchParameters);
    }
}
