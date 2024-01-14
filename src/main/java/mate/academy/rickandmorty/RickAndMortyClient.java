package mate.academy.rickandmorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character/?name=%s";

    private final ObjectMapper objectMapper;

    public RickAndMortyClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterInfoDto> getCharacters(String characterName) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = URL.formatted(characterName);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            CharacterResponseDataDto dataDto = objectMapper.readValue(
                    response.body(),
                    CharacterResponseDataDto.class
            );
            return dataDto.getResults().stream().toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
