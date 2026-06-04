package mate.academy.rickandmorty.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyApiClientImpl implements ApiClient {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @Override
    public String getPage(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = HTTP_CLIENT.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );

            return response.body();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Request interrupted", e);
        }

    }
}
