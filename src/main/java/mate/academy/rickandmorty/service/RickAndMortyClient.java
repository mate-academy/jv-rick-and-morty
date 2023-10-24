package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterMetaDto;
import mate.academy.rickandmorty.dto.external.CreateCharacterDtoList;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    private final CharacterService characterService;

    public void getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterMetaDto metaDto = objectMapper.readValue(
                    response.body(), CharacterMetaDto.class);
            int countOfPages = Integer.parseInt(metaDto.info().get("pages"));
            for (int i = 1; i <= countOfPages; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(URL + "/?page=" + i))
                        .build();
                response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CreateCharacterDtoList dtoList = objectMapper.readValue(
                        response.body(), CreateCharacterDtoList.class);
                characterService.saveAll(dtoList.getResults());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
