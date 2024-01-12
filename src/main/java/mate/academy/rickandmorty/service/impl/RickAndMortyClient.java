package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponseDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResultDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private static String url = "https://rickandmortyapi.com/api/character?page=0";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        loadCharactersToDb();
    }

    private RickAndMortyApiResponseDto getCharactersInfo(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            RickAndMortyApiResponseDto responseDto
                    = objectMapper.readValue(response.body(), RickAndMortyApiResponseDto.class);
            System.out.println(responseDto);
            return responseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCharactersToDb() {
        RickAndMortyApiResponseDto responseDto = getCharactersInfo(url);
        while (responseDto.getInfo().next() != null) {
            List<RickAndMortyResultDto> results = responseDto.getResults();
            List<Character> characterList = results.stream()
                    .map(characterMapper::toModel)
                    .toList();
            characterRepository.saveAll(characterList);
            url = responseDto.getInfo().next();
            responseDto = getCharactersInfo(url);
        }
    }
}
