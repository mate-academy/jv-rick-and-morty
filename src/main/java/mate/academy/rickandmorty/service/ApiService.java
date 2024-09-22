package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.ApiResponse;
import mate.academy.rickandmorty.model.CharacterInfo;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final RickAndMortyRepository repository;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    @PostConstruct
    public void fetchAndSaveAllCharacters() throws IOException, InterruptedException {
        String nextUrl = BASE_URL;
        List<CharacterInfo> characters = new ArrayList<>();

        while (nextUrl != null) {
            HttpResponse<String> response = getResponse(nextUrl);
            ApiResponse apiResponse = parseToApiResponse(response);
            characters.addAll(apiResponse.getResults());
            nextUrl = apiResponse.getInfo().getNext();
        }
        repository.saveAll(characters);
    }

    private HttpResponse<String> getResponse(String url)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private ApiResponse parseToApiResponse(HttpResponse<String> response)
            throws JsonProcessingException {
        return objectMapper.readValue(response.body(), ApiResponse.class);
    }
}
