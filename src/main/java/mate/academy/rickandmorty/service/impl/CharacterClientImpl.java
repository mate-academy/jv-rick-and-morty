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
    private static final int DEFAULT_PAGE = 1;
    private static final String PAGE = "/?page=%s";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    public void getAllCharacters() {
        int pages = getResponseDataDto(DEFAULT_PAGE).getInfo().getPages();
        for (int i = DEFAULT_PAGE; i <= pages; i++) {
            CharacterResponseDataDto responseDto = getResponseDataDto(i);
            saveCharacters(responseDto);
        }
    }

    private void saveCharacters(CharacterResponseDataDto responseDto) {
        List<Character> characters = responseDto.getResults().stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
    }

    private CharacterResponseDataDto getResponseDataDto(int page) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + PAGE.formatted(page)))
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
