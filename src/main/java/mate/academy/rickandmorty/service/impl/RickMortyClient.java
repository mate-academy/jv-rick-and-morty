package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.external.RickMortyApiResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RickMortyClient {

    public static final String BASE_URL = "https://rickandmortyapi.com/api/%s";

    private final ObjectMapper objectMapper;

    public RickMortyClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterApiResponseDto> loadAllCharactersFromExternal() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = String.format(BASE_URL, "character");

            List<CharacterApiResponseDto> allCharacters = new ArrayList<>();
            String nextPageUrl = url;

            while (nextPageUrl != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextPageUrl))
                        .build();

                HttpResponse<String> response =
                        client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                RickMortyApiResponseDto apiResponse = objectMapper.readValue(response.body(),
                        RickMortyApiResponseDto.class);

                if (apiResponse.getResults() != null) {
                    allCharacters.addAll(apiResponse.getResults());
                }
                nextPageUrl = apiResponse.getResponseInfo() != null
                        ? apiResponse.getResponseInfo().getNext() : null;
            }
            return allCharacters;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Exception while fetching characters from API ", e);
        }
    }

}

