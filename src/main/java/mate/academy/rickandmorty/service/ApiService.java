package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @PostConstruct
    void configureObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T get(String url, Class<T> clazz) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), clazz);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Can’t fetch info from URL: " + url, e);
        }
    }
}
