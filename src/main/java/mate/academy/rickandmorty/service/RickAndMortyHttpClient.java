package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyHttpClient {
    private static final String BASE_HTTP_LINK_BY_PAGE_WITH_CHARACTERS =
            "https://rickandmortyapi.com/api/character/?page=";
    private final ObjectMapper objectMapper;

    public RickAndMortyHttpClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    public List<CharacterDto> getAllCharactersFromPages() {
        List<CharacterDto> characterDtos = new ArrayList<>();
        int currentPage = 0;
        int amountPages = 0;
        do {
            final HttpClient httpClient = HttpClient.newHttpClient();
            final HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_HTTP_LINK_BY_PAGE_WITH_CHARACTERS + ++currentPage))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString());
                RickAndMortyResponseDto rickAndMortyResponseDto = objectMapper
                        .readValue(response.body(), RickAndMortyResponseDto.class);
                characterDtos.addAll(rickAndMortyResponseDto.getResults());
                amountPages = rickAndMortyResponseDto.getInfo().getPages();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } while (currentPage != amountPages);
        return characterDtos;
    }
}
