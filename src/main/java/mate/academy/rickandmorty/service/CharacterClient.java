package mate.academy.rickandmorty.service;

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
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${character.api.url}")
    private String apiUrl;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void init() {
        List<CharacterEntity> charactersList = getCharacters().stream()
                .map(characterInfoDto -> {
                    CharacterEntity characterEntity = characterMapper.toCharacter(characterInfoDto);
                    characterEntity.setExternalId(characterInfoDto.getId());
                    return characterEntity;
                })
                .toList();
        characterRepository.saveAll(charactersList);
    }

    public List<CharacterInfoDto> getCharacters() {
        List<CharacterInfoDto> allCharacters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;
        while (apiUrl != null) {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto = objectMapper
                        .readValue(response.body(), CharacterResponseDataDto.class);
                String nextUrl = characterResponseDataDto.getInfo().next();
                allCharacters.addAll(characterResponseDataDto.getResults());
                apiUrl = nextUrl;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return allCharacters;
    }
}
