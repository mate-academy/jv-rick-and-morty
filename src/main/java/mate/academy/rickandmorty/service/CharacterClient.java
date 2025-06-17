package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RnMResponseDataDto;
import mate.academy.rickandmorty.dto.external.RnMResponseResultDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String CHARACTERS_URL
            = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<RnMResponseResultDto> getRnMData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CHARACTERS_URL))
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            RnMResponseDataDto dataDto = objectMapper
                    .readValue(response.body(), RnMResponseDataDto.class);
            return dataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not get characters from API", e);
        }
    }
}
