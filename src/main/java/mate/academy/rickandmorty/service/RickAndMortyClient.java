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
import mate.academy.rickandmorty.dto.external.CharacterResultResponse;
import mate.academy.rickandmorty.exception.RequestProcessingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {

    private final ObjectMapper objectMapper;
    private final List<CharacterResultResponse> characterResultResponseList = new ArrayList<>();

    public List<CharacterResultResponse> getCharacters(String url) {
        CharacterResultResponse characterResultResponse;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            characterResultResponse
                    = objectMapper.readValue(response.body(), CharacterResultResponse.class);
            characterResultResponseList.add(characterResultResponse);
            if (characterResultResponse.getInfo().getNextUrl() != null) {
                getCharacters(characterResultResponse.getInfo().getNextUrl());
            }
        } catch (IOException | InterruptedException e) {
            throw new RequestProcessingException("Something went wrong, you cannot send "
                    + "a request or receive a"
                    + " response from the Rick and Morty API, please "
                    + "fix the problem and try again", e);
        }
        return characterResultResponseList;
    }
}
