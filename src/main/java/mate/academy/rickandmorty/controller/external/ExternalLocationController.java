package mate.academy.rickandmorty.controller.external;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/external/location")
public class ExternalLocationController {
    private final LocationClient locationClient;

    @GetMapping
    @Operation(summary = "Get all locations",
            description = "Get all available locations + pagination")
    public LocationsDto getLocations() throws IOException, InterruptedException {
        return locationClient.getLocations();
    }

    @GetMapping("/{ids}")
    @Operation(summary = "Get locations by ids", description = "Get locations by ids")
    public List<LocationDto> getLocationsByIds(@PathVariable Integer... ids)
            throws IOException, InterruptedException {
        return locationClient.getLocationsByIds(ids);
    }

    @GetMapping("/search")
    @Operation(summary = "Search locations by parameters",
            description = "Search locations by name, type, dimension")
    public LocationsDto getLocationsByParams(LocationSearchParameters locationSearchParameters)
            throws IOException, InterruptedException {
        return locationClient.getLocationByParams(locationSearchParameters);
    }
}
