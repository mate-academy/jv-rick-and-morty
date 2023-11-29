package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDtoResult;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<ExternalCharacterDtoResult> getAllCharactersFromExternalDataBase () {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ExternalCharacterDto externalCharacterDto = objectMapper.readValue(response.body(), ExternalCharacterDto.class);
            List<ExternalCharacterDtoResult> characters = new ArrayList<>(externalCharacterDto.getResults());
            while (externalCharacterDto.getInfo().next() != null) {
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(externalCharacterDto.getInfo().next()))
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                externalCharacterDto = objectMapper.readValue(response.body(), ExternalCharacterDto.class);
                characters.addAll(externalCharacterDto.getResults());
            }
            return characters;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
