package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exception.CanNotGetDataFromExternalApi;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharactersClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final CharacterService characterService;

    @PostConstruct
    public void init() {
        getListWithExternalApi();
    }

    private void getListWithExternalApi() {
        String url = BASE_URL;
        while (url != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDataDto.class);
                characterResponseDataDto.results()
                        .stream().map(createCharacterRequestDto ->
                                characterService.save(createCharacterRequestDto))
                        .toList();
                url = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new CanNotGetDataFromExternalApi("Can't get data from API", e);
            }
        }
    }
}
