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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.CharacterRepositoryMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${api.character.url}")
    private String externalApiUrl;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        List<Character> charactersList = getCharacters().stream()
                .map(characterResponseDto -> {
                    Character character = characterMapper.toCharacter(characterResponseDto);
                    character.setExternalId(characterResponseDto.id());
                    return character;
                })
                .toList();
        CharacterRepositoryMetadata repositoryMetadata
                = applicationContext.getBean(CharacterRepositoryMetadata.class);
        repositoryMetadata.setNumberOfCharacters(charactersList.size());
        characterRepository.saveAll(charactersList);
    }

    private List<CharacterResponseDto> getCharacters() {
        List<CharacterResponseDto> charactersList = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;
        while (externalApiUrl != null) {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(externalApiUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto = objectMapper
                        .readValue(response.body(), CharacterResponseDataDto.class);
                externalApiUrl = characterResponseDataDto.getInfo().next();
                charactersList.addAll(characterResponseDataDto.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return charactersList;
    }
}
