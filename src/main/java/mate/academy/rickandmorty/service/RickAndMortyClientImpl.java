package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;
import mate.academy.rickandmorty.dto.external.SeriesCharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClientImpl implements RickAndMortyClient {
    private static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private static final String RICKANDMORTY_API_BASE_URL =
            "https://rickandmortyapi.com/api%s?page=%s";
    private static final String CHARACTER_PATH = "/character";
    private final ObjectMapper objectMapper;

    public List<SeriesCharacterDto> getCharacters(int page) {
        HttpClient client = HttpClient.newHttpClient();
        String url = RICKANDMORTY_API_BASE_URL.formatted(CHARACTER_PATH, page);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            SeriesCharacterResponseDataDto characterResponseDataDto = objectMapper
                    .readValue(response.body(), SeriesCharacterResponseDataDto.class);
            return characterResponseDataDto.getResults().stream().toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCharacterPageCount() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CHARACTERS_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            SeriesCharacterResponseDataDto seriesCharacterResponseDataDto = objectMapper
                    .readValue(response.body(), SeriesCharacterResponseDataDto.class);
            return seriesCharacterResponseDataDto.getInfo().pages();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
