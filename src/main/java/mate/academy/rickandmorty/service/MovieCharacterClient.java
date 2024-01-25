package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterClient {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponseDto getResponseDto(String url) {
        ApiResponseDto apiResponseDto;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            apiResponseDto = objectMapper.readValue(
                    httpResponse.body(),
                    new TypeReference<ApiResponseDto>() {}
                    );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can`t fetch info from URL: " + url, e);
        }
        return apiResponseDto;
    }
}
