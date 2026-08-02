package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FigureClient {

    private static final String BASE_URL =
            "https://rickandmortyapi.com/api/character/?page=%s";

    private static final int MAX_RETRY = 3;
    private static final long RETRY_DELAY = 30000;

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public FigureResponseDataDto getFiguresFromApi(int page) {

        String url = BASE_URL.formatted(page);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        for (int attempt = 1; attempt <= MAX_RETRY; attempt++) {

            try {

                HttpResponse<String> response =
                        httpClient.send(
                                request,
                                HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {

                    return objectMapper.readValue(
                            response.body(),
                            FigureResponseDataDto.class);
                }

                System.out.println(
                        "API error: " + response.statusCode());

            } catch (IOException | InterruptedException e) {

                if (attempt == MAX_RETRY) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Thread.sleep(RETRY_DELAY);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        throw new RuntimeException(
                "Cannot download page " + page);
    }
}
