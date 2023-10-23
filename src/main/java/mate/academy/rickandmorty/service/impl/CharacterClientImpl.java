package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.exception.RequestException;
import mate.academy.rickandmorty.dto.external.CharactersResultListDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String FORMATED_STRING = "?page=";
    @Value("${external.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;
    private final CharacterRepository repository;
    private final CharacterMapper characterMapper;

    @Override
    public void getCharacterMetaInfo() {
        int index = 1;
        String url = baseUrl.formatted(FORMATED_STRING + index++);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            CharactersResultListDto dataDto = objectMapper.readValue(response.body(),
                    CharactersResultListDto.class);
            List<Character> model = characterMapper.toModel(dataDto.getResults());
            repository.saveAll(model);
            saveCharacters(dataDto, client, index);
        } catch (IOException | InterruptedException e) {
            throw new RequestException("Error occurred during receiving response.", e);
        }
    }

    private void saveCharacters(CharactersResultListDto dataDto, HttpClient client, int index) {
        while (dataDto.getInfo().getNext() != null) {
            String url = baseUrl.formatted(FORMATED_STRING + index++);
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                dataDto = objectMapper.readValue(response.body(), CharactersResultListDto.class);
                List<Character> model = characterMapper.toModel(dataDto.getResults());
                repository.saveAll(model);
            } catch (IOException | InterruptedException e) {
                throw new RequestException("Error occurred during receiving response.", e);
            }
        }
    }
}
