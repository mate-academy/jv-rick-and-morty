package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import mate.academy.rickandmorty.exeption.UnableGettingAllCharactersException;
import mate.academy.rickandmorty.exeption.UnableGettingCountOfCharactersException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/%s";
    private static final Long FIRST_CHARACTER = 1L;
    private final ObjectMapper objectMapper;

    private Long getCountOfCharacter() {
        String formatted = String.format(BASE_URL, "");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(formatted))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), RickAndMortyResponseDto.class)
                    .getInfo().getCount();
        } catch (IOException | InterruptedException e) {
            throw new UnableGettingCountOfCharactersException("Can`t get "
                    + "count of characters by url " + formatted, e);
        }
    }

    public List<CharacterDto> getCharacters() {
        long lastCharacter = getCountOfCharacter();
        String allIds = LongStream
                .range(FIRST_CHARACTER, lastCharacter + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        String formatted = String.format(BASE_URL,allIds);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(formatted))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(),
                    new TypeReference<>() {});
        } catch (IOException | InterruptedException e) {
            throw new UnableGettingAllCharactersException("Can`t get all character by url "
                    + formatted, e);
        }
    }
}
