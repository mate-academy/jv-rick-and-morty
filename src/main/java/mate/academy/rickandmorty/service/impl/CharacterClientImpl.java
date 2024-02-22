package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfo;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {

    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;


    public List<CharacterInfo> getCharacters() {
        List<CharacterInfo> resultList = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String nextUrl = URL;
        try {
            while (nextUrl != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextUrl))
                        .build();
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto =
                        objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                resultList.addAll(characterResponseDataDto.getResults());
                nextUrl = characterResponseDataDto.getInfo().getNext();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get characters from API.");
        }
        return resultList;
    }
}
