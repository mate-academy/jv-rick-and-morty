package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDtoWithoutExternalId;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.service.ApplicationRunnerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationRunnerServiceImpl implements ApplicationRunnerService {
    public static final String RICK_AND_MORTY_CHARACTERS_URL =
            "https://rickandmortyapi.com/api/character?page=%d";
    private static final int PAGE_ONE = 1;
    private static final int PAGE_LAST = 42;

    private final ObjectMapper objectMapper;

    @Override
    public List<CharacterDtoWithoutExternalId> getCharactersFromApi() {
        List<CharacterDtoWithoutExternalId> characterDtoList = new ArrayList<>();
        for (int page = PAGE_ONE; page <= PAGE_LAST; page++) {
            characterDtoList.addAll(handleHttpRequest(getHttpRequest(page)));
        }
        return characterDtoList;
    }

    private HttpRequest getHttpRequest(int page) {
        return HttpRequest.newBuilder().GET()
                .uri(URI.create(RICK_AND_MORTY_CHARACTERS_URL.formatted(page))).build();
    }

    private List<CharacterDtoWithoutExternalId> handleHttpRequest(HttpRequest httpRequest) {
        try {
            HttpResponse<String> response = getHttpResponse(httpRequest);
            RickAndMortyCharacterDto charactersOnPage = getCharactersOnPage(response.body());
            return charactersOnPage.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't handle http request! ", e);
        }
    }

    private HttpResponse<String> getHttpResponse(HttpRequest httpRequest)
            throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private RickAndMortyCharacterDto getCharactersOnPage(String responseBody)
            throws JsonProcessingException {
        return objectMapper.readValue(responseBody, RickAndMortyCharacterDto.class);
    }
}
