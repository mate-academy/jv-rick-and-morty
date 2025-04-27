package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.dto.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.service.CharacterCartoon;
import org.springframework.stereotype.Component;

@Component
public class CharacterCartoonImpl implements CharacterCartoon {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public CharacterCartoonImpl(ObjectMapper objectMapper, CharacterMapper characterMapper) {
        this.objectMapper = objectMapper;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharactersDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto = objectMapper.readValue(response.body(),
                    CharacterResponseDataDto.class);
            List<RickAndMortyApiCharacterDto> apiCharacterDtos = Optional
                    .ofNullable(dataDto.getResults())
                    .orElse(Collections.emptyList());

            return apiCharacterDtos.stream()
                    .map(characterMapper::fromApiToDto)
                    .toList();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
