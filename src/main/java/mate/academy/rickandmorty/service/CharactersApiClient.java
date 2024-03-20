package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterDtoFromApi;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharactersApiClient {
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public ApiResponseDto getAllCharacterFromApi(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            ApiResponseDto apiResponseDto = objectMapper
                    .readValue(httpResponse.body(), ApiResponseDto.class);
            List<CharacterDtoFromApi> list = Arrays.stream(apiResponseDto.getResults())
                    .map(characterMapper::toCharacterDtoForDb)
                    .toList();
            return apiResponseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
