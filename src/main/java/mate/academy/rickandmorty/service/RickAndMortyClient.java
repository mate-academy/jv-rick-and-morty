package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void init() {
        if (characterRepository.count() == 0) {
            getRickAndMorty();
        }
    }

    public void getRickAndMorty() {
        String url = URL;
        HttpClient httpClient = HttpClient.newHttpClient();
        List<RickAndMortyCharacterDto> characterDtoList = new ArrayList<>();
        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                RickAndMortyResponseDataDto responseDataDto = objectMapper.readValue(
                        httpResponse.body(), RickAndMortyResponseDataDto.class
                );
                characterDtoList.addAll(responseDataDto.data());
                url = responseDataDto.info().next();
            }
            characterRepository.saveAll(characterMapper.toModelList(characterDtoList));
        } catch (IOException | InterruptedException e) {
            throw new EntityNotFoundException("Can't get data from external API", e);
        }
    }
}
