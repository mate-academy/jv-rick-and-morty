package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.api.ApiResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApiClient {
    private final ObjectMapper mapper;
    private final HttpClient httpClient;

    public ApiResponseDto fetchDataFromApi(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                                             .GET()
                                             .uri(URI.create(url))
                                             .build();
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), ApiResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get response from API by URL: " + url, e);
        }
    }
}
