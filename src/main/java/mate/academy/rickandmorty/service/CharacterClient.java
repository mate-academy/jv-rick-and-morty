package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterPageDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final String USER_AGENT = "jv-rick-and-morty-app";

    private final ObjectMapper objectMapper;

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterResponseDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<CharacterResponseDto> characterAccumulator = new ArrayList<>();
        String nextUrl = BASE_URL;

        while (nextUrl != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextUrl))
                    .header("User-Agent", USER_AGENT)
                    .build();
            try {
                Thread.sleep(1000);
                HttpResponse<String> httpResponse = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterPageDto characterPageDto = objectMapper.readValue(httpResponse.body(),
                        CharacterPageDto.class);

                characterAccumulator.addAll(characterPageDto.results());

                nextUrl = characterPageDto.info().getNext();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            } catch (IOException e) {
                throw new RuntimeException("Can't get characters", e);
            }
        }

        return characterAccumulator;
    }
}
