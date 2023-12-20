package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.ResponseInfoDto;
import mate.academy.rickandmorty.dto.external.ResponseSeriesCharacterDataDto;
import mate.academy.rickandmorty.dto.external.SeriesCharacterResponseDto;
import mate.academy.rickandmorty.service.SeriesCharacterService;
import org.springframework.stereotype.Component;

@Component
public class SeriesCharacterClient {
    private static final String GET_ALL_CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final SeriesCharacterService seriesCharacterService;
    private final List<ResponseSeriesCharacterDataDto> seriesCharacterDataDtos = new ArrayList<>();

    public SeriesCharacterClient(ObjectMapper objectMapper,
                                 SeriesCharacterService seriesCharacterService) {
        this.objectMapper = objectMapper;
        this.seriesCharacterService = seriesCharacterService;
    }

    public void loadSeriesCharacters() {
        ResponseInfoDto info = null;
        String url = GET_ALL_CHARACTERS_URL;
        int i = 0;
        do {
            info = sendRequest(url);
            i++;
            url = info.next();
        } while (i < info.pages());

        seriesCharacterService.saveAll(seriesCharacterDataDtos);
    }

    private ResponseInfoDto sendRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            SeriesCharacterResponseDto seriesCharacterResponseDto =
                    objectMapper.readValue(response.body(), SeriesCharacterResponseDto.class);

            ResponseInfoDto info = seriesCharacterResponseDto.info();
            seriesCharacterDataDtos.addAll(seriesCharacterResponseDto.results());

            return info;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
