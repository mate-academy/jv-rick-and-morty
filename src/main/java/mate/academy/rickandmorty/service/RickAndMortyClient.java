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
import mate.academy.rickandmorty.dto.external.CharacterRateDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private final ObjectMapper objectMapper;

    public List<CharacterRateDto> getAllCharacter() {
        HttpClient httpClient = HttpClient.newHttpClient();
        int page = 1;
        List<CharacterRateDto> fetchedCharacters = new ArrayList<>();
        while (page > 0) {
            HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                    .uri(URI.create(BASE_URL + page)).build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterRateDto dataDto = objectMapper.readValue(response.body(),
                        CharacterRateDto.class);
                if (dataDto.name() == null) {
                    break;
                }
                fetchedCharacters.add(dataDto);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            page++;
        }
        return fetchedCharacters;
    }
}
