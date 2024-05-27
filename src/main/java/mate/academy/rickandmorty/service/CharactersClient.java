package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper mapper;
    private final CharacterService characterService;

    @PostConstruct
    public void dbInit() {
        getCharactersInfo();
    }

    public void getCharactersInfo() {
        HttpClient httpClient = HttpClient.newHttpClient();
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
                CharacterResponseDataDto characterResponseDataDto = mapper.readValue(
                        response.body(),
                        CharacterResponseDataDto.class);
                characterResponseDataDto.results()
                        .stream().map(createCharacterRequestDto ->
                                characterService.save(createCharacterRequestDto))
                        .toList();
                url = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get data from API", e);
            }
        }
    }
}
