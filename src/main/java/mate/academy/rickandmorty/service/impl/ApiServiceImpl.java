package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.ApiResponse;
import mate.academy.rickandmorty.service.ApiService;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import mate.academy.rickandmorty.service.mapper.CharacterMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final CartoonCharacterService characterService;
    private final CharacterMapper mapper;
    private final ObjectMapper objectMapper;
    private final Environment environment;

    @Override
    public void fetch() {
        String currentUrl = environment.getProperty("api.url");
        ApiResponse response = getApiResponse(currentUrl);

        while (currentUrl != null) {
            characterService.saveAll(
                    response.getResults()
                            .stream()
                            .map(mapper::toModel)
                            .collect(Collectors.toList())
            );

            currentUrl = response.getInfo().getNext();
            response = getApiResponse(currentUrl);
        }

        System.out.println("Data fetching is done");
    }

    private ApiResponse getApiResponse(String url) {
        if (url == null) {
            return null;
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(
                            httpRequest,
                            HttpResponse.BodyHandlers.ofString()
                    );
            return objectMapper.readValue(response.body(), ApiResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not execute request by url: " + url, e);
        }
    }
}
