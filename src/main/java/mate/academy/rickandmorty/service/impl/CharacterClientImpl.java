package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    @Value("${rick.and.morty.base.url}")
    private String baseUrl;

    @Override
    public List<CharacterResponseDto> getAllCharacters() {
        ApiResponseDataDto apiResponseDataDto = getCharacters(baseUrl);
        List<CharacterResponseDto> characters = apiResponseDataDto.results();
        String apiUrl = apiResponseDataDto.info().next();
        while (apiUrl != null) {
            apiResponseDataDto = getCharacters(apiUrl);
            characters.addAll(apiResponseDataDto.results());
            apiUrl = apiResponseDataDto.info().next();
        }
        return characters;
    }

    private ApiResponseDataDto getCharacters(String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), ApiResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while retrieving characters from API: ", e);
        }
    }
}
