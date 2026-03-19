package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import mate.academy.rickandmorty.exception.CharacterFetchingException;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%s";
    private static final int DEFAULT_PAGE = 1;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterExternalDto> getAllCharacters() {
        List<CharacterExternalDto> characters = new ArrayList<>();
        RickAndMortyResponseDto firstPageResponse = requestByPage(DEFAULT_PAGE);
        int pages = firstPageResponse.getInfo().getPages();

        characters.addAll(firstPageResponse.getResults());

        for (int i = DEFAULT_PAGE + 1; i <= pages; i++) {
            RickAndMortyResponseDto responseDto = requestByPage(i);
            characters.addAll(responseDto.getResults());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CharacterFetchingException(e);
            }
        }

        return characters;
    }

    private RickAndMortyResponseDto requestByPage(int page) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(BASE_URL, page)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new CharacterFetchingException(
                        "Request failed. Status: " + response.statusCode()
                                + ", body: " + response.body()
                );
            }

            return objectMapper.readValue(
                    response.body(),
                    RickAndMortyResponseDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new CharacterFetchingException(e);
        }

    }
}
