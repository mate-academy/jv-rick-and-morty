package mate.academy.rickandmorty.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Configuration
public class DataFetchConfig {
    private final CharacterService service;
    private final ObjectMapper objectMapper;
    @Value("${rickandmorty.api.url}")
    private String characterUrl;

    @EventListener(ApplicationReadyEvent.class)
    public void fetchData() {
        List<ExternalCharacterDto> characters = new ArrayList<>();
        CharacterResponseDataDto response = loadData(characterUrl);
        do {
            characters.addAll(response.results());
            response = loadData(response.info().next());
        } while (response.info().next() != null);
        service.saveAll(characters);
    }

    private CharacterResponseDataDto loadData(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<byte[]> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofByteArray()
            );
            return objectMapper.readValue(
                    response.body(), new TypeReference<>() {
                    }
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
