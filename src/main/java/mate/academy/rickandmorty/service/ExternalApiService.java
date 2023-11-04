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
import mate.academy.rickandmorty.dto.external.PageResponseDto;
import mate.academy.rickandmorty.mapper.MovieCharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalApiService {
    private final MovieCharacterMapper movieCharacterMapper;
    private final ObjectMapper objectMapper;

    public List<MovieCharacter> getListOfMovieCharacter(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<MovieCharacter> characters = new ArrayList<>();
        while (url != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                PageResponseDto pageResponseDto = objectMapper.readValue(
                        response.body(), PageResponseDto.class);
                pageResponseDto.results().stream()
                        .map(movieCharacterMapper::toModel)
                        .forEach(characters::add);
                url = pageResponseDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get all movie character");
            }
        }
        return characters;
    }
}
