package mate.academy.rickandmorty.service.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.location.LocationDto;
import mate.academy.rickandmorty.dto.external.location.LocationSearchParameters;
import mate.academy.rickandmorty.dto.external.location.LocationsDto;
import mate.academy.rickandmorty.service.client.LocationClient;
import mate.academy.rickandmorty.service.http.impl.HttpResponseStringService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationClientImpl implements LocationClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/location";
    private final ObjectMapper objectMapper;
    private final HttpResponseStringService httpResponseStringService;

    @Override
    public LocationsDto getLocations() throws IOException, InterruptedException {
        return objectMapper.readValue(httpResponseStringService.getHttpResponse(BASE_URL).body(),
                LocationsDto.class);
    }

    @Override
    public List<LocationDto> getLocationsByIds(Integer... ids)
            throws IOException, InterruptedException {
        String url = BASE_URL + "/" + Arrays.toString(ids)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
        return ids.length == 1 ? List.of(objectMapper.readValue(httpResponseStringService
                .getHttpResponse(url).body(), LocationDto.class))
                : objectMapper.readValue(httpResponseStringService.getHttpResponse(url).body(),
                    new TypeReference<>() {
                    });
    }

    @Override
    public LocationsDto getLocationByParams(LocationSearchParameters locationSearchParameters)
            throws IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL + "/?");
        if (locationSearchParameters.getName() != null) {
            urlBuilder.append("name=").append(locationSearchParameters.getName()
                    .replace(" ", "+")).append("&");
        }
        if (locationSearchParameters.getType() != null) {
            urlBuilder.append("type=").append(locationSearchParameters.getType()
                    .replace(" ", "+")).append("&");
        }
        if (locationSearchParameters.getDimension() != null) {
            urlBuilder.append("dimension=")
                    .append(locationSearchParameters.getDimension()
                            .replace(" ", "+")).append("&");
        }
        return objectMapper.readValue(httpResponseStringService
                .getHttpResponse(urlBuilder.toString()).body(), LocationsDto.class);
    }
}
