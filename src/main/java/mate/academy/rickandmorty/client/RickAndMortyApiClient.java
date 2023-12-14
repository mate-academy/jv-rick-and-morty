package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharactersResponseDataDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Component
public class RickAndMortyApiClient {
    private static final String BASIC_CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private static final String PAGEABLE_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;

    public CharactersResponseDataDto getBasicCharactersData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getBasicInfoRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASIC_CHARACTERS_URL))
                .build();

        try {
            HttpResponse<String> basicInfoResponse = httpClient.send(
                    getBasicInfoRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            return objectMapper.readValue(
                    basicInfoResponse.body(),
                    CharactersResponseDataDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get basic info", e);
        }
    }
}
