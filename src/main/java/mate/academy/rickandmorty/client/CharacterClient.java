package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromApiDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final int FIRST_PAGE = 1;
    private static final int AVAILABLE_PAGES_COUNT = 42;

    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        List<CharacterFromApiDto> allCharacters = getAllCharacters();
        characterRepository.saveAll(
                allCharacters.stream()
                        .map(characterMapper::toModel)
                        .toList()
        );
    }

    public List<CharacterFromApiDto> getAllCharacters() {
        List<CharacterFromApiDto> allCharacters = new ArrayList<>();

        for (int currentPage = FIRST_PAGE; currentPage <= AVAILABLE_PAGES_COUNT; currentPage++) {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL + "?page=" + currentPage))
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
                throw new RuntimeException(e);
            }
        }
        return allCharacters;
    }
}
