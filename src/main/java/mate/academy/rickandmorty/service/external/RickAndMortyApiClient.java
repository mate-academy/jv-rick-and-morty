package mate.academy.rickandmorty.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharactersDataResponseDto;
import mate.academy.rickandmorty.exception.RetrievingDataException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    public static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper mapper;

    public CharactersDataResponseDto getData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return mapper.readValue(body, CharactersDataResponseDto.class);
        } catch (IOException | InterruptedException e) {
            if (Thread.currentThread().isInterrupted()) {
                Thread.currentThread().interrupt();
            }
            throw new RetrievingDataException("Can't retrieve data "
                    + "from the external API " + url, e);
        }
    }
}
