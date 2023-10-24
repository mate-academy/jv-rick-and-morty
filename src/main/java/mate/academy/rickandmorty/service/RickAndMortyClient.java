package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.dto.internal.InternalCharListDto;
import mate.academy.rickandmorty.dto.internal.InternalCharResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String GET_CHAR_BASE_URL = "https://rickandmortyapi.com/api/character/%s";
    private static final String GET_ALL_BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;

    public ExternalCharResponseDto getRandomCharacter() {
        Random random = new Random();
        int maxCharAmount = 826;
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = GET_CHAR_BASE_URL.formatted(String.valueOf(random.nextInt(maxCharAmount)));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            InternalCharResponseDto internalCharResponseDto = objectMapper.readValue(
                    response.body(), InternalCharResponseDto.class
            );
            if (!characterRepository.findAll().isEmpty()) {
                for (Character c : characterRepository.findAll()) {
                    if (c.getExternalId().equals(internalCharResponseDto.id())) {
                        return characterMapper.toDto(c);
                    }
                }
            }
            Character saved = characterRepository
                    .save(characterMapper.toEntity(internalCharResponseDto));
            return characterMapper.toDto(saved);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public InternalCharListDto getAllCharacters(Integer page) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = UriComponentsBuilder
                .fromHttpUrl(GET_ALL_BASE_URL)
                .queryParam("page", page)
                .toUriString();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(
                    response.body(),
                    InternalCharListDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
