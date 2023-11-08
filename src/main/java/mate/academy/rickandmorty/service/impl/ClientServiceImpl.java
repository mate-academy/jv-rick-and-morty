package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.ClientService;
import mate.academy.rickandmorty.service.UrlService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// R - resource data page
public class ClientServiceImpl<R> implements ClientService<R> {
    private final UrlService urlServices;
    private final ObjectMapper objectMapper;

    @Override
    public R getPageFromResourceName(String resourceName, Class<R> className) {
        return fetchDataFromResponse(urlServices.getResourcesUrl(resourceName), className);
    }

    @Override
    public R getPageFromUrl(String url, Class<R> className) {
        return fetchDataFromResponse(url, className);
    }

    private R fetchDataFromResponse(String url, Class<R> className) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            final HttpResponse<String> response =
                    client.send(getHttpRequest(url), HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), className);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching data from URL", e);
        }
    }

    private HttpRequest getHttpRequest(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
    }
}
