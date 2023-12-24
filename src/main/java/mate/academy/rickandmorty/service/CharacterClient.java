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
import mate.academy.rickandmorty.dto.external.ApiResponseData;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {

    private static final String URL_CHARACTERS = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    public List<CharacterResponseDto> getCharacters() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_CHARACTERS))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            ApiResponseData responseData =
                    objectMapper.readValue(response.body(), ApiResponseData.class);
            List<CharacterResponseDto> dtoList = new ArrayList<>(responseData.getResults());
            while (responseData.getInfo().next() != null) {
                responseData = getCharacter(responseData.getInfo().next());
                dtoList.addAll(responseData.getResults());
            }
            return dtoList;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error when retrieving characters from external API: " + e);
        }
    }

    private ApiResponseData getCharacter(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            ApiResponseData responce =
                    objectMapper.readValue(response.body(), ApiResponseData.class);
            return responce;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error when retrieving characters from external API: " + e);
        }
    }
}
