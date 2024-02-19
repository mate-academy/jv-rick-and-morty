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
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalResponseDto> getCharacters() {
        int perPage = 20;
        int currentPage = 1;
        int numberOfPages;
        List<CharacterExternalResponseDto> allCharacters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        while (true) {
            String urlWithPagination = BASE_URL + "?page=" + currentPage + "&per_page=" + perPage;
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlWithPagination))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(
                        request, HttpResponse.BodyHandlers.ofString());

                CharacterResponseDataDto characterResponseDataDto = objectMapper.readValue(
                        response.body(), CharacterResponseDataDto.class);

                allCharacters.addAll(characterResponseDataDto.getResults());
                numberOfPages = characterResponseDataDto.getInfo().getNumberOfPages();

                if (currentPage == numberOfPages) {
                    break;
                }
                currentPage++;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return allCharacters;
    }
}
