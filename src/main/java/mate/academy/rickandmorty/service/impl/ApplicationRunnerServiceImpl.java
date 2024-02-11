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
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
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
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    private final List<CharacterDtoWithoutExternalId> characterDtoList = new ArrayList<>();

    @Override
    public void processCharactersFromApi() {
        for (int page = PAGE_ONE; page <= PAGE_LAST; page++) {
            handleHttpRequest(getHttpRequest(page));
        }
        saveAllCharactersToDb();
    }

    private HttpRequest getHttpRequest(int page) {
        return HttpRequest.newBuilder().GET()
                .uri(URI.create(RICK_AND_MORTY_CHARACTERS_URL.formatted(page))).build();
    }

    private void handleHttpRequest(HttpRequest httpRequest) {
        try {
            HttpResponse<String> response = getHttpResponse(httpRequest);
            RickAndMortyCharacterDto charactersOnPage = getCharactersOnPage(response.body());
            characterDtoList.addAll(charactersOnPage.getResults());
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

    private void saveAllCharactersToDb() {
        characterRepository.saveAll(
                characterDtoList.stream()
                .map(characterMapper::toEntity)
                .toList()
        );
    }
}
