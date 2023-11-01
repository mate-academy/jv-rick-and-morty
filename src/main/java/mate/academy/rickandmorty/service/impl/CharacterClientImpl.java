package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String PAGE_IN_URL_STRING = "?page=";
    @Value("${external.url}")
    private String baseUrl;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;

    public void getAllCharacters() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl))
                .build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto infoDto
                    = objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
            int countOfPages = infoDto.getInfo().getPages();
            for (int i = 1; i < countOfPages; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(baseUrl + PAGE_IN_URL_STRING + i))
                        .build();
                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dtos
                        = objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                for (CharacterResponseDto dto : dtos.getResults()) {
                    saveCharacter(dto);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveCharacter(CharacterResponseDto dto) {
        Character character = new Character();
        character.setExternalId(dto.id());
        character.setName(dto.name());
        character.setStatus(dto.status());
        character.setGender(dto.gender());
        characterRepository.save(character);
    }
}
