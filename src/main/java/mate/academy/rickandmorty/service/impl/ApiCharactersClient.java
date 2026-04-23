package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponse;
import mate.academy.rickandmorty.dto.external.CharacterResponseResultsDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiCharactersClient implements CharactersClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient;

    public List<CharacterResponseResultsDto> fetchCharactersFromApi() {
        List<CharacterResponseResultsDto> result = new ArrayList<>();

        String url = BASE_URL;

        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new DataProcessingException(
                            "API error: " + response.statusCode() + " body: " + response.body()
                    );
                }

                CharacterApiResponse apiResponse =
                        objectMapper.readValue(response.body(), CharacterApiResponse.class);

                if (apiResponse == null || apiResponse.results() == null) {
                    continue;
                }

                result.addAll(apiResponse.results());

                url = apiResponse.info().next();

                // Needed for external api not shutting the connection
                Thread.sleep(500);
            }

            return result;

        } catch (Exception e) {
            throw new DataProcessingException("Cannot fetch characters from api", e);
        }
    }
}
