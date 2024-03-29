package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.GetCharactersResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApi {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void getAllCharacters() {
        String urlToNextPage = BASE_URL + "/character";

        do {
            GetCharactersResponseDto dataDto = fetchCharacters(urlToNextPage);
            saveAll(dataDto.results());

            urlToNextPage = dataDto.info().next();
        } while (urlToNextPage != null);
    }

    private void saveAll(List<CharacterDto> results) {

        List<Character> list = results.stream()
                .map(characterMapper::toModel)
                .toList();

        characterRepository.saveAll(list);
    }

    private GetCharactersResponseDto fetchCharacters(String urlToNextPage) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlToNextPage))
                .build();

        try {
            HttpResponse<String> response =
                    HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), GetCharactersResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
