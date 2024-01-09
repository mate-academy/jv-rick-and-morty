package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RickAndMortyApiClient {
    private static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character";

    public List<CharacterResponseDataDto> getCharactersInfo() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CHARACTERS_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
