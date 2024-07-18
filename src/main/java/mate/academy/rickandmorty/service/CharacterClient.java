package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public List<CharacterDataDto> getDataDtos() {
        String url = BASE_URL;
        List<CharacterDataDto> characterDataDtos = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

        try {
            while (url != null) {
                HttpResponse<String> response = httpClient.send(httpRequest,
                            HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto = objectMapper
                            .readValue(response.body(), CharacterResponseDataDto.class);

                characterDataDtos.addAll(characterResponseDataDto.getData());

                url = characterResponseDataDto.getInfo().getNext();
            }
            characterDataDtos
                .stream()
                .map(characterMapper::toModel)
                        .forEach(characterRepository::save);
            return characterDataDtos;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
