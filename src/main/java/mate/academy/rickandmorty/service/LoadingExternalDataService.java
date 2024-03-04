package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseResultsDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadingExternalDataService {
    private static int countCharacters = 0;
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public List<CharacterResponseResultsDto> loadingExternalData() {

        List<CharacterResponseResultsDto> responseResultsDtos = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = URL;
        try {
            while (Objects.nonNull(url)) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());

                CharacterResponseDataDto characterResponseDataDto = objectMapper
                        .readValue(response.body(), CharacterResponseDataDto.class);

                responseResultsDtos.addAll(characterResponseDataDto.results());
                url = characterResponseDataDto.info().next();
            }
            countCharacters = responseResultsDtos.size();
            return responseResultsDtos;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void init() {
        List<CharacterResponseResultsDto> responseResultsDtos = loadingExternalData();
        characterRepository.saveAll(responseResultsDtos
                .stream()
                .map(characterMapper::toModel)
                .toList());
    }

    public static int getCountCharacters() {
        return countCharacters;
    }
}
