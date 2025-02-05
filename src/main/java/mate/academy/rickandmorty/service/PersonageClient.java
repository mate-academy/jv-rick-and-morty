package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonageClient {
    private final ObjectMapper mapper;

    public ResponseDto getPersonages(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );

            return mapper.readValue(response.body(), ResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(
                    "An unexpected error occurred while getting data: " + e.getMessage(), e);
        }
    }
}
