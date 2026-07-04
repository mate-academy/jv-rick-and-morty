package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.RickAndMortyResponseDto;
import mate.academy.rickandmorty.exception.DataFetchException;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    public static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CharacterDto> getJsonResponse() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            RickAndMortyResponseDto rickAndMortyResponseDto =  objectMapper.readValue(json,
                    RickAndMortyResponseDto.class);
            return rickAndMortyResponseDto.getListOfCharacters();
        } catch (IOException | InterruptedException e) {
            throw new DataFetchException("Can't fetch data from external API ", e);
        }
    }
}
