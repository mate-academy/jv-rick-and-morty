package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyApiClient {
    private static final String PAGEABLE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private static final String CHARACTER_NOT_FOUND_MSG = "Character not found.";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterResponseDto> getAllCharacters() {
        int totalPages = getCharactersFromPage(1).getInfo().getPages();
        return getAllPages(totalPages)
                .stream()
                .flatMap(page -> page.getResults().stream())
                .collect(Collectors.toList());
    }

    private List<CharacterResponseInfoDto> getAllPages(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .mapToObj(this::getCharactersFromPage)
                .collect(Collectors.toList());
    }

    private CharacterResponseInfoDto getCharactersFromPage(int pageNumber) {
        String url = PAGEABLE_URL.formatted(pageNumber);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), CharacterResponseInfoDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(CHARACTER_NOT_FOUND_MSG, e);
        }
    }
}
