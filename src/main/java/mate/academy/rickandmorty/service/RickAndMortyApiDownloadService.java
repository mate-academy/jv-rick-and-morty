package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.RickAndMorty;
import mate.academy.rickandmorty.repository.character.RickAndMortyRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiDownloadService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final RickAndMortyRepository repository;
    private final RickAndMortyMapper mapper;

    @EventListener(ApplicationReadyEvent.class)
    public void importCharacter() {
        if (repository.count() > 0) {
            return;
        }
        String nextUrl = URL;
        while (nextUrl != null) {
            try {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI(nextUrl))
                        .build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode root = objectMapper.readTree(response.body());
                    JsonNode results = root.get("results");

                    List<RickAndMorty> characters = new ArrayList<>();
                    for (JsonNode node : results) {
                        ExternalCharacterDto characterDto = objectMapper.treeToValue(node,
                                ExternalCharacterDto.class);
                        characters.add(mapper.toModel(characterDto));
                    }
                    repository.saveAll(characters);
                    JsonNode info = root.get("info");
                    nextUrl = info.get("next").isNull() ? null : info.get("next").asText();
                }
            } catch (IOException | InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
