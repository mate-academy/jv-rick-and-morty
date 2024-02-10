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
import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.dto.CharacterListApiDto;
import mate.academy.rickandmorty.exception.ThirdPartyApiException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 42;

    private final ObjectMapper objectMapper;

    public List<CharacterApiDto> getAllHeroFromApi() {
        List<CharacterApiDto> allHero = new ArrayList<>();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            for (int page = FIRST_PAGE; page <= LAST_PAGE; page++) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(CHARACTER_URL + "?page=" + page))
                        .build();
                HttpResponse<String> response = httpClient
                        .send(
                                httpRequest,
                                HttpResponse.BodyHandlers.ofString()
                        );
                CharacterListApiDto characterListApiDto = objectMapper
                        .readValue(
                                response.body(),
                                CharacterListApiDto.class
                        );
                allHero.addAll(characterListApiDto.getResults());
            }
        } catch (IOException | InterruptedException e) {
            throw new ThirdPartyApiException("can't get all character", e);
        }
        return allHero;
    }
}
