package mate.academy.rickandmorty.service.impl;

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
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient implements ApiClient {
    @Value("${external.api.base.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;
    private final CharacterRepository repository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        repository.saveAll(characterMapper.toModel(fetchData()));
    }

    @Override
    public List<CharacterApiResponseDto> fetchData() {
        RickAndMortyResponseDataDto characterData = getCharacterData(baseUrl);
        List<CharacterApiResponseDto> characterList = new ArrayList<>(characterData.results());
        while (characterData.info().next() != null) {
            characterData = getCharacterData(characterData.info().next());
            characterList.addAll(characterData.results());
        }
        return characterList;
    }

    @Override
    public RickAndMortyResponseDataDto getCharacterData(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), RickAndMortyResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
