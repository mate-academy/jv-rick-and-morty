package mate.academy.rickandmorty.service.impl;

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
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;

    @Override
    public List<CharacterResultDto> getAllCharacters() {
        String url = BASE_URL;
        List<CharacterResultDto> allCharacters = new ArrayList<>();
        int countPage = 1;
        while (url != null) {
            CharacterResponseDataDto data = getData(url.formatted(countPage));
            allCharacters.addAll(data.resultsDto());
            url = data.infoDto().getNext();
            countPage++;
        }
        return allCharacters;
    }

    private CharacterResponseDataDto getData(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper
                    .readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
