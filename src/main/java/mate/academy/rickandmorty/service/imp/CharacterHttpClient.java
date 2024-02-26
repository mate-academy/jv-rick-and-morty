package mate.academy.rickandmorty.service.imp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterHttpClient {
    private final ObjectMapper objectMapper;

    public <T> T get(String url, Class<? extends T> clazz) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        T resultValue = null;
        try {
            HttpResponse<String> result = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            resultValue = objectMapper.readValue(result.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't execute get request from: " + url, e);
        }
        return resultValue;
    }
}
