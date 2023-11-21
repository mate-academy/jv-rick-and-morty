package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterClientImpl implements CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final String PAGE = "/?page=%s";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    public void getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int pages = objectMapper.readValue(response.body(), CharacterResponseDataDto.class)
                    .getInfo().getPages();
            CharacterResponseDataDto responseDto;
            for (int i = 1; i <= pages; i++) {
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(BASE_URL + PAGE.formatted(i)))
                        .build();
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                responseDto = objectMapper.readValue(response.body(),
                        CharacterResponseDataDto.class);
                saveCharacters(responseDto);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveCharacters(CharacterResponseDataDto responseDto) {
        List<Character> characters = responseDto.getResults().stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
    }
}
