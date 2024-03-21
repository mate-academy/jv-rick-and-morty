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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.ResultsResponseDataDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterResponseDataDto> getCharacters() {
        List<CharacterResponseDataDto> characters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String page = URL;
        while (page != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(page))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString()
                );
                ResultsResponseDataDto resultsResponseDataDto = objectMapper
                        .readValue(response.body(), ResultsResponseDataDto.class);
                characters.addAll(resultsResponseDataDto.results());
                if (resultsResponseDataDto.infoResponseDto() != null
                        && resultsResponseDataDto.infoResponseDto().next() != null) {
                    page = resultsResponseDataDto.infoResponseDto().next();
                } else {
                    page = null;
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't send http request", e);
            }
        }
        return characters;
    }
}
