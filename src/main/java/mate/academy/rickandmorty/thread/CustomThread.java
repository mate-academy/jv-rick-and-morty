package mate.academy.rickandmorty.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RequestDto;
import mate.academy.rickandmorty.dto.external.RequestResultsDto;

@RequiredArgsConstructor
public class CustomThread implements Callable<List<RequestResultsDto>> {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final int pageNumber;

    @Override
    public List<RequestResultsDto> call() {
        HttpRequest pageRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + pageNumber))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    pageRequest, HttpResponse.BodyHandlers.ofString());
            RequestDto dataDto = objectMapper.readValue(response.body(), RequestDto.class);
            return dataDto.results();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
