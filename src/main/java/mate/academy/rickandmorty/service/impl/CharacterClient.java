package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RequestDto;
import mate.academy.rickandmorty.dto.external.RequestResultsDto;
import mate.academy.rickandmorty.thread.CustomThread;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=";

    private final ObjectMapper objectMapper;

    public List<RequestResultsDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<RequestResultsDto> characters;
        int pages;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + 1))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            RequestDto dataDto = objectMapper.readValue(
                    response.body(), RequestDto.class);
            pages = dataDto.info().pages();
            characters = new ArrayList<>(dataDto.results());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(pages);
        List<CompletableFuture<List<RequestResultsDto>>> futureRequests = new ArrayList<>();
        for (int i = 2; i <= pages; i++) {
            Future<List<RequestResultsDto>> future = executorService.submit(
                    new CustomThread(httpClient, objectMapper, i));
            CompletableFuture<List<RequestResultsDto>> completableFuture =
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            futureRequests.add(completableFuture);
        }
        executorService.shutdown();
        List<RequestResultsDto> additionalCharacters = futureRequests.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .toList();

        characters.addAll(additionalCharacters);
        return characters;
    }
}
