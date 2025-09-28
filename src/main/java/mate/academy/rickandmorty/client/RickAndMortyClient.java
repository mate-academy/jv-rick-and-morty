package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDataDto;
import mate.academy.rickandmorty.dto.CharacterDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public List<CharacterDto> getCharacters() {
        List<CharacterDto> allCharacters = new ArrayList<>();
        String url = BASE_URL;
        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                        .uri(URI.create(url)).build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() < 200 || response.statusCode() >= 300) {
                    throw new RuntimeException("Failed to fetch data from API. URL: "
                            + url + ", Status: " + response.statusCode()
                            + ", Body: " + response.body());
                }

                CharacterDataDto characterResponse = objectMapper
                        .readValue(response.body(), CharacterDataDto.class);
                allCharacters.addAll(characterResponse.getResults());
                url = characterResponse.getInfo().getNext();
            }
            return allCharacters;

        } catch (IOException e) {
            throw new RuntimeException("Couldn't get characters from API", e);
        } catch (InterruptedException e) {
            // ИСПРАВЛЕНИЕ: Восстанавливаем статус прерывания потока,
            // как того требуют лучшие практики Java.
            Thread.currentThread().interrupt();
            throw new RuntimeException("API request was interrupted", e);
        }
    }
}
