package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public RickAndMortyResponseDto fetchDataFromApi(String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = httpClient
                    .send(httpRequest,
                            HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(),
                    RickAndMortyResponseDto.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(
                    "Can't fetch data from Rick and Morty API by URL: " + url, e);
        }
    }

}
