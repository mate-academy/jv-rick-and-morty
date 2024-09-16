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
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersClientImpl implements CharactersClient {
    private static final String URL_BASE = "https://rickandmortyapi.com/api/character/";

    private final ObjectMapper objectMapper;

    @Override
    public List<CharacterDto> findAll() {
        String url = URL_BASE;
        List<CharacterDto> allCharacters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dataDto = objectMapper.readValue(response.body(),
                        CharacterResponseDataDto.class);

                allCharacters.addAll(dataDto.getResults());
                url = dataDto.getInfo().getNext();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return allCharacters;
    }
}
