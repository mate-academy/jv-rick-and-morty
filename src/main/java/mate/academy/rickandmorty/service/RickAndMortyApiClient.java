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
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=";
    private final ObjectMapper objectMapper;
    @Value("${rick.and.morty.characters.last.page}")
    private int lastPage;

    public List<CharacterResultDto> fetchAllData() {
        List<CharacterResultDto> allCharacter = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        int currentPage = 1;
        while (currentPage <= lastPage) {
            String url = BASE_URL + currentPage;
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto =
                        objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                allCharacter.addAll(characterResponseDataDto.getResults());
                currentPage++;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to fetch data from external API"
                        + " or parse response.", e);
            }
        }
        return allCharacter;
    }
}
