package mate.academy.rickandmorty.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.request.CharacterRequestDto;
import mate.academy.rickandmorty.dto.request.RickAndMortyRequestDto;
import mate.academy.rickandmorty.exception.HttpClientException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterRequestDto> fetchCharacters() {
        try {
            List<CharacterRequestDto> characterDtos = new LinkedList<>();
            RickAndMortyRequestDto data = fetchDataFrom(API_URL);
            
            while (true) {
                characterDtos.addAll(data.results());
                
                if (data.info().next() == null) {
                    break;
                }

                data = fetchDataFrom(data.info().next());
            }

            return characterDtos;
        } catch (IOException | InterruptedException e) {
            throw new HttpClientException(e.getMessage());
        }
    }

    private RickAndMortyRequestDto fetchDataFrom(String url) throws IOException, 
                                                                    InterruptedException {
        return objectMapper.readValue(
            httpClient.send(
                HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create(url))
                            .build(),
                HttpResponse.BodyHandlers.ofString()
            ).body(),
            RickAndMortyRequestDto.class
        );
    }
}
