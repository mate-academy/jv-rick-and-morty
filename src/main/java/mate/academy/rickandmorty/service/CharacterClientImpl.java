package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String PAGE_URL = "?page=";
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;

    public void getAllCharacters() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto infoDto
                    = objectMapper.readValue(response.body(),
                    CharacterResponseDataDto.class);

            int countOfPages = infoDto.getInfo().getPages();
            for (int i = 1; i < countOfPages; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(BASE_URL + PAGE_URL + i))
                        .build();

                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dtos
                        = objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                for (CharacterResponseDto dto : dtos.getResults()) {
                    Character character = new Character();
                    character.setExternalId(dto.id());
                    character.setName(dto.name());
                    character.setStatus(dto.status());
                    character.setGender(dto.gender());
                    characterRepository.save(character);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
