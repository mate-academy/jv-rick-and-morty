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
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final int NUMBER_OF_PAGES = 42;
    private static final String basicUrl = "https://rickandmortyapi.com/api/character?page=%d";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalDto> getCharacters() {
        List<CharacterExternalDto> charactersFromApi = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        for (int i = 1; i <= NUMBER_OF_PAGES; i++) {
            String url = String.format(basicUrl, i);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dataDto =
                        objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                System.out.println(dataDto.getResults());
                charactersFromApi.addAll(dataDto.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return charactersFromApi;
    }
}
