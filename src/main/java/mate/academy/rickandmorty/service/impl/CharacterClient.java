package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RequestDto;
import mate.academy.rickandmorty.dto.external.RequestResultsDto;
import mate.academy.rickandmorty.thread.GetRequestDtoThread;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final int INITIAL_PAGE = 1;

    private final ObjectMapper objectMapper;

    public List<RequestResultsDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<RequestResultsDto> characters = new ArrayList<>();
        int pages = getPagesCountAndFirstPageResults(httpClient, characters);
        completeCharacterList(httpClient, characters, pages);
        return characters;
    }

    private int getPagesCountAndFirstPageResults(
            HttpClient httpClient, List<RequestResultsDto> characters) {
        RequestDto firstPage =
                new GetRequestDtoThread(httpClient, objectMapper, INITIAL_PAGE).call();
        characters.addAll(firstPage.results());
        return firstPage.info().pages();
    }

    private void completeCharacterList(
            HttpClient httpClient, List<RequestResultsDto> characters, int pages) {
        List<CompletableFuture<List<RequestResultsDto>>> futureRequests = new ArrayList<>();
        futureRequests.add(CompletableFuture.supplyAsync(() ->
                IntStream.range(INITIAL_PAGE + 1, pages + 1)
                    .mapToObj(page ->
                        CompletableFuture.supplyAsync(() ->
                            new GetRequestDtoThread(httpClient, objectMapper, page).call()))
                    .map(requestDtoCompletableFuture ->
                        requestDtoCompletableFuture.join().results())
                    .flatMap(List::stream)
                    .collect(Collectors.toList())
        ));

        List<RequestResultsDto> additionalCharacters = futureRequests.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .toList();
        characters.addAll(additionalCharacters);
    }
}
