package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterLoader;
import org.springframework.stereotype.Component;

@Component
public class CharacterLoaderImpl implements CharacterLoader {
    private static final String EXTERNAL_API_URL = "https://rickandmortyapi.com/api/character/";
    private final HttpClient httpClient;
    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public CharacterLoaderImpl(CharacterRepository characterRepository,
                               ObjectMapper objectMapper,
                               CharacterMapper characterMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public void loadDataFromExternalApi() {
        String nextPageUrl = EXTERNAL_API_URL;

        while (nextPageUrl != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextPageUrl))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());
                if (response.body() != null) {
                    ExternalCharacterApiResponseDto responseDto = objectMapper
                            .readValue(response.body(),
                                    ExternalCharacterApiResponseDto.class);
                    CharacterResponseDataDto[] charactersDto = responseDto.getResults();
                    Character[] characters = Arrays.stream(charactersDto)
                            .map(characterMapper::toCharacter)
                            .toArray(Character[]::new);
                    characterRepository.saveAll(Arrays.asList(characters));
                    System.out.println("Data from page loaded successfully.");

                    nextPageUrl = responseDto.getInfo().getNext();
                } else {
                    System.out.println("Failed to load data from page.");
                    break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("All data loaded successfully.");
    }
}
