package mate.academy.rickandmorty.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClientImpl implements CharacterClient {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void getAndSaveCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = API_URL;
        List<ExternalCharacterDto> externalCharacterDtoList = new ArrayList<>();

        do {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto dataDto = objectMapper
                        .readValue(response.body(), CharacterResponseDto.class);

                url = dataDto.getInfo().getNextUrl();
                externalCharacterDtoList.addAll(dataDto.getResult().stream().toList());

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (url != null);

        Iterable<Character> characters = externalCharacterDtoList.stream()
                .map(characterMapper::toModel)
                .toList();

        characterRepository.saveAll(characters);
    }
}
