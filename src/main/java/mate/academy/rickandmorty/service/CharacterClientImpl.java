package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import org.springframework.stereotype.Service;

@Service
public class CharacterClientImpl implements CharacterClient {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public CharacterClientImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ExternalCharacterDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CHARACTER_URL))
                .build();
        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return Collections.singletonList(objectMapper.readValue(response.body(),
                            ExternalCharacterDto.class));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
