package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponse;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiCharactersClient implements CharactersClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient;

    public List<CharacterResponseDto> fetchCharactersFromApi() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterApiResponse apiResponse =
                    objectMapper.readValue(response.body(), CharacterApiResponse.class);

            if (apiResponse == null || apiResponse.results() == null) {
                return List.of();
            }

            return apiResponse.results();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot fetch characters from api", e);
        }
    }
}
