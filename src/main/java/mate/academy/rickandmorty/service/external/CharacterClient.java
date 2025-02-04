package mate.academy.rickandmorty.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;
import mate.academy.rickandmorty.service.internal.CharacterService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterService characterService;

    public void fetchAndSaveAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        List<CharacterExternalResponseDto> allCharacters = new ArrayList<>();

        while (url != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> httpResponse = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterApiResponseDto apiResponse = objectMapper
                        .readValue(httpResponse.body(), CharacterApiResponseDto.class);
                allCharacters.addAll(apiResponse.getResults());
                url = apiResponse.getInfo().getNext();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        allCharacters.stream()
                .map(dto -> new CreateCharacterRequestDto(
                        dto.getExternalId(),
                        dto.getName(),
                        dto.getStatus(),
                        dto.getGender()))
                .forEach(characterService::save);
    }
}
