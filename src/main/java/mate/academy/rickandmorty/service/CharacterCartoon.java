package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.dto.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterCartoon {

    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public List<CharactersDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> responce = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto = objectMapper.readValue(responce.body(),
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

