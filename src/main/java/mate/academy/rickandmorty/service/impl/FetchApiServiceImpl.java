package mate.academy.rickandmorty.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mate.academy.rickandmorty.service.FetchApiService;
import org.springframework.stereotype.Service;

@Service
public class FetchApiServiceImpl implements FetchApiService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public String fetchDataFromApi(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
