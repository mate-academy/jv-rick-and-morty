package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public CharacterResponseDataDto getAllCharacters() {
        CharacterResponseDataDto allCharacters = new CharacterResponseDataDto();

        for (int currentPage = 1; currentPage <= 42; currentPage++) {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(URL + "?page=" + currentPage))
                    .build();

            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto pageData = objectMapper
                        .readValue(response.body(), CharacterResponseDataDto.class);

                allCharacters.getResults().addAll(pageData.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return allCharacters;
    }
}

