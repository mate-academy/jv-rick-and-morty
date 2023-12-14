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
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterDto> getAllCharacters() {
        CharacterResponseInfoDto infoDto = getCharacterInfoFromBaseUrl();
        List<CharacterDto> dtos = new ArrayList<>(infoDto.getResults());
        try {
            while (infoDto.getInfo().getNext() != null) {
                HttpResponse<String> response = httpClient
                        .send(httpRequestByUrl(infoDto.getInfo().getNext()),
                        HttpResponse.BodyHandlers.ofString());
                infoDto = objectMapper.readValue(response.body(),
                        CharacterResponseInfoDto.class);
                dtos.addAll(infoDto.getResults());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Characters not found.", e);
        }
        return dtos;
    }

    private CharacterResponseInfoDto getCharacterInfoFromBaseUrl() {
        CharacterResponseInfoDto infoDto = null;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            infoDto = objectMapper.readValue(response.body(), CharacterResponseInfoDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Character info not found.", e);
        }
        return infoDto;
    }

    private HttpRequest httpRequestByUrl(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
    }
}
