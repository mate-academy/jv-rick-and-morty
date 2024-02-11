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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final int TOTAL_PAGES = 42;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterResponseInfoDto> getAllCharacters() {
        List<CharacterResponseInfoDto> allCharacters = new ArrayList<>();

        for (int currentPage = 1; currentPage <= TOTAL_PAGES; currentPage++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(String.format("%s?page=%d", BASE_URL, currentPage)))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                CharacterResponseDataDto charactersPerPage = objectMapper.readValue(
                        response.body(), CharacterResponseDataDto.class
                );

                allCharacters.addAll(charactersPerPage.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get info about characters on page: "
                        + currentPage, e);
            }
        }
        return allCharacters;
    }
}
