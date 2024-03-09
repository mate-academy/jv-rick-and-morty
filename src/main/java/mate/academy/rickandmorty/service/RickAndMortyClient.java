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
import mate.academy.rickandmorty.dto.external.ExternalCharacterResultResponse;
import mate.academy.rickandmorty.exception.RequestProcessingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private final ObjectMapper objectMapper;
    private final List<ExternalCharacterResultResponse> externalCharacterResultResponseList
            = new ArrayList<>();

    public List<ExternalCharacterResultResponse> getCharacters(String url) {
        ExternalCharacterResultResponse externalCharacterResultResponse;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            externalCharacterResultResponse
                    = objectMapper.readValue(response.body(),
                    ExternalCharacterResultResponse.class);
            externalCharacterResultResponseList.add(externalCharacterResultResponse);
            if (externalCharacterResultResponse.getInfo().getNextUrl() != null) {
                getCharacters(externalCharacterResultResponse.getInfo().getNextUrl());
            }
        } catch (IOException | InterruptedException e) {
            throw new RequestProcessingException("Something went wrong, you cannot send "
                    + "a request or receive a"
                    + " response from the Rick and Morty API, please "
                    + "fix the problem and try again", e);
        }
        return externalCharacterResultResponseList;
    }
}
