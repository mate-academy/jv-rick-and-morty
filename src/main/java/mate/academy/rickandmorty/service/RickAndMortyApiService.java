package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RickAndMortyApiService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    private List<CharacterResponseDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto responseDto = objectMapper.readValue(
                    response.body(),
                    CharacterResponseDataDto.class);
            return responseDto.result();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Occurred an Error during fetching data from API", e);
        }
    }
}
