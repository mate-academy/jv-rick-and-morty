package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_PATH = "character";
    private static final String PAGE_NUMBER_PARAM = "?page=";
    private final ObjectMapper objectMapper;

    @Value("${rickmorty.api.url}")
    private String rickAndMortyUrl;

    public ExternalCharacterDataDto getCharacterDataAt(int page) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(createUri(page))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );
            return objectMapper.readValue(
                    response.body(), ExternalCharacterDataDto.class
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URI createUri(int page) {
        String url = new StringBuilder(rickAndMortyUrl)
                                        .append("/")
                                        .append(BASE_PATH)
                                        .append(PAGE_NUMBER_PARAM)
                                        .append(page)
                                        .toString();
        return URI.create(url);
    }
}
