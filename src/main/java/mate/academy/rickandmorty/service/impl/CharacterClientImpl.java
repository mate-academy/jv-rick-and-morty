package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.exception.RequestException;
import mate.academy.rickandmorty.dto.external.CharactersResultListDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String FORMATTED_STRING = "?page=";
    @Value("${external.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;
    private final CharacterRepository repository;
    private final CharacterMapper characterMapper;
    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public void initCharacterInfo() {
        int index = 1;
        fetchCharacterData(index);
    }

    private void fetchCharacterData(int index) {
        String url = baseUrl.formatted(FORMATTED_STRING + index);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    try {
                        return objectMapper.readValue(json, CharactersResultListDto.class);
                    } catch (Exception e) {
                        throw new RequestException("Error parsing JSON response", e);
                    }
                })
                .thenAccept(dataDto -> {
                    List<Character> model = characterMapper.toModel(dataDto.getResults());
                    repository.saveAll(model);

                    if (dataDto.getInfo().getNext() != null) {
                        fetchCharacterData(index + 1);
                    }
                });
    }
}
