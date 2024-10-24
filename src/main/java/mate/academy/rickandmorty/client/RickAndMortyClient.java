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
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import mate.academy.rickandmorty.exception.HttpRequestException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public List<CreateCharacterDto> getAllCharactersFromApi() {
        List<CreateCharacterDto> characters = new ArrayList<>();
        String pageUrl = BASE_URL;

        ResponseDto response;
        do {
            response = getPageWithCharacters(pageUrl);
            characters.addAll(response.characters());
            pageUrl = response.info().next();
        } while (pageUrl != null);

        return characters;
    }

    private ResponseDto getPageWithCharacters(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> send
                    = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(send.body(), ResponseDto.class);

        } catch (IOException | InterruptedException e) {
            throw new HttpRequestException("Failed to send https request to url: " + url, e);
        }
    }
}
