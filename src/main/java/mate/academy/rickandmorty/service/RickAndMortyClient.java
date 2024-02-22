package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.external.ResultsResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalResponseDto> getCharacters() {
        ArrayList<CharacterExternalResponseDto> characters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String page = BASE_URL;
        while (page != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(page))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());

                ResultsResponseDto resultsResponseDto =
                        objectMapper.readValue(response.body(), ResultsResponseDto.class);

                characters.addAll(resultsResponseDto.results());
                page = resultsResponseDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't send Http request", e);
            }
        }
        return characters;
    }
}
