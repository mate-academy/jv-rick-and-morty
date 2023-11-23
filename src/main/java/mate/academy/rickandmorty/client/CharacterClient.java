package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterRequest;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public List<Character> getAllCharacters() {
        List<CharacterRequest> characterRequestList = new ArrayList<>();
        int index = 1;
        while (index < 43) {
            HttpRequest httpRequest
                    = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL.formatted(index)))
                    .build();
            try {
                HttpResponse<String> httpResponse
                        = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterRequest characterRequest = objectMapper.readValue(httpResponse.body(), CharacterRequest.class);
                characterRequestList.add(characterRequest);
                index++;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get all characters " + e);
            }
        }
        return characterRequestList.stream()
                .flatMap(c -> c.getResults()
                        .stream())
                .map(characterMapper::toModel).toList();
    }
}
