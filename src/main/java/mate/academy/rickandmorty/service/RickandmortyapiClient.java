package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickandmortyapiCharacterDto;
import mate.academy.rickandmorty.dto.external.RickandmortyapiResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickandmortyapiClient {
    private String url = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final List<RickandmortyapiCharacterDto> characters = new LinkedList<>();

    public List<RickandmortyapiCharacterDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                RickandmortyapiResponseDataDto dataDto = objectMapper.readValue(response.body(),
                        RickandmortyapiResponseDataDto.class);

                characters.addAll(dataDto.results());
                url = dataDto.info().next();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get data from client", e);
        }
        return characters;
    }
}
