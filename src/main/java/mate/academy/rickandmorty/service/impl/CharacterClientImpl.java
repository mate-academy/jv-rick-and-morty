package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDetailsDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterDao;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClientImpl implements CharacterClient {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterDao characterDao;
    private final CharacterMapper characterMapper;

    @Override
    public void fetchAndSaveCharacterData() throws RuntimeException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String pageUrl = API_URL;
        List<CharacterDetailsDto> listOfCharacters = new ArrayList<>();
        do {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(pageUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dataDto = objectMapper
                        .readValue(response.body(), CharacterResponseDataDto.class);
                pageUrl = dataDto.getInfo().getNext();
                listOfCharacters.addAll(dataDto.getResults().stream().toList());
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        } while (pageUrl != null);
        Iterable<Character> characters =
                listOfCharacters.stream()
                        .map(characterMapper::toModel)
                        .toList();
        characterDao.saveAll(characters);
    }
}
