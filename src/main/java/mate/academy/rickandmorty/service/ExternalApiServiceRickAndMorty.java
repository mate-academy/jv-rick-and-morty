package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalApiServiceRickAndMorty {
    private static final String URL_CHARACTER = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void fetchCharacters() {
        String page = URL_CHARACTER;
        while (page != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(page))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto responseCharacterDataDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDto.class);
                responseCharacterDataDto.getResults().stream()
                        .map(characterMapper::toModel)
                        .forEach(characterRepository::save);
                page = responseCharacterDataDto.getInfo().getNext();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get data from API", e);
            }
        }
    }
}
