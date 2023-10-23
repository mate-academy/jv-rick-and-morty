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
import mate.academy.rickandmorty.dto.external.CharacterPageResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClient {
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    @Value("${rick-and-morty-api}")
    private String url;

    public void saveAllCharactersFromApi() {
        List<CharacterModel> characters = getAllCharacters();
        characterRepository.saveAll(characters);
    }

    private List<CharacterModel> getAllCharacters() {
        List<CharacterModel> characters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String nextPage = url;
        do {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextPage))
                    .build();
            try {
                HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterPageResponseDto pageResponseDto =
                        objectMapper.readValue(httpResponse.body(), CharacterPageResponseDto.class);
                pageResponseDto.results()
                        .stream()
                        .map(characterMapper::toModel)
                        .forEach(characters::add);
                nextPage = pageResponseDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Unsuccessful request sending", e);
            }
        } while (nextPage != null);
        return characters;
    }

    @PostConstruct
    private void init() {
        if (characterRepository.count() == 0) {
            saveAllCharactersFromApi();
        }
    }
}
