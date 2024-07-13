package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharactersDataResponseDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String PAGE_DELIMITER = "?page=";
    private static final int PAGES_NUMBER = 42;
    private static int CURRENT_PAGE = 1;
    private final ObjectMapper objectMapper;
    @Value("${rick-and-morty-url}")
    private String characterUrl;

    @Override
    public List<CharacterResponseDto> findAllCharacters() {
        List<CharacterResponseDto> characters = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        while (CURRENT_PAGE <= PAGES_NUMBER) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(characterUrl + PAGE_DELIMITER + CURRENT_PAGE))
                    .build();
            try {
                HttpResponse<String> response = client.send(
                        request, HttpResponse.BodyHandlers.ofString());
                characters.addAll(parseResponse(response.body()).results());
                CURRENT_PAGE++;
            } catch (IOException | InterruptedException e) {
                throw new DataProcessingException("Can't send request to get data", e);
            }
        }
        return characters;
    }

    private CharactersDataResponseDto parseResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, CharactersDataResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new DataProcessingException("Can't parse response body.", e);
        }
    }
}
