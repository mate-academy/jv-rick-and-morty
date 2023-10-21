package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ListOfCharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClientImpl implements RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/%s";
    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    @Override
    public void fetchAllCharactersFromThirdPartyApi() {
        int page = 1;
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted("?page=" + page++);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );
            ListOfCharacterResponseDto responseDto = objectMapper.readValue(
                    response.body(), ListOfCharacterResponseDto.class
            );
            List<Character> entityList = characterMapper.toEntityList(responseDto.getResults());
            characterRepository.saveAll(entityList);
            saveNextCharacters(page, responseDto, httpClient);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error occurred during receiving response", e);
        }
    }

    private void saveNextCharacters(
            int page,
            ListOfCharacterResponseDto responseDto,
            HttpClient httpClient
    ) {
        while (responseDto.getInfo().getNext() != null) {
            String url = BASE_URL.formatted("?page=" + page++);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString()
                );
                responseDto = objectMapper.readValue(
                        response.body(), ListOfCharacterResponseDto.class
                );
                List<Character> entityList = characterMapper.toEntityList(responseDto.getResults());
                characterRepository.saveAll(entityList);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error occurred during receiving response", e);
            }
        }
    }
}
