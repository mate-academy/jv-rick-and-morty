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
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharactersApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<ApiCharacterDto> getAllCharacterFromApi() {
        List<ApiCharacterDto> apiCharacterDtoList = new ArrayList<>();
        String nextPage = BASE_URL;

        while (nextPage != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextPage))
                    .build();
            try {
                HttpResponse<String> httpResponse = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                ApiResponseDto apiResponseDto = objectMapper
                        .readValue(httpResponse.body(), ApiResponseDto.class);
                apiCharacterDtoList.addAll(apiResponseDto.getResults());
                nextPage = apiResponseDto.getApiInfoDto().getNext();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return apiCharacterDtoList;
    }
}
