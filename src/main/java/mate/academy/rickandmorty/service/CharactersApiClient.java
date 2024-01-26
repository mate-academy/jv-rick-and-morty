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
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersApiClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterFromExternalApiDto> getAllCharactersFromApi() {
        HttpClient httpClient = HttpClient.newHttpClient();

        int perPage = 20;
        int currentPage = 1;
        int numberOfPages;
        List<CharacterFromExternalApiDto> allCharacters = new ArrayList<>();

        while (true) {
            String urlWithPagination = URL + "?page=" + currentPage + "&per_page=" + perPage;

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlWithPagination))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dataDto = objectMapper.readValue(
                        response.body(), CharacterResponseDataDto.class);

                allCharacters.addAll(dataDto.getResults());
                numberOfPages = dataDto.getInfo().getNumberOfPages();

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
