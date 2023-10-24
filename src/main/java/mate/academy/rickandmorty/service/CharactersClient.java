package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResults;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Value("${mate.academy.get.all.characters.url}")
    private String getAllCharactersUrl;

    @PostConstruct
    private List<Character> downloadAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(getAllCharactersUrl))
                .build();
        CharacterResponseDataDto dataDto;
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            dataDto = objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        characterRepository.deleteAll();
        List<CharacterResults> characterResults = dataDto.getResults();
        List<Character> characterList = characterResults.stream()
                .map(characterMapper::fromApiToDto)
                .map(characterMapper::toModel)
                .toList();
        return characterRepository.saveAll(characterList);
    }
}
