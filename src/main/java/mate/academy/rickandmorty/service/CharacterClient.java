package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import mate.academy.rickandmorty.dto.external.ExternalCharacterResponseDto;
import mate.academy.rickandmorty.dto.external.InfoResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CharacterClient {
    private static final String URL_BASE = "https://rickandmortyapi.com/api/character/";
    private final ObjectMapper objectMapper;

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ExternalCharacterResponseDto getAllCharacters() {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;
        String nextUrl = URL_BASE;
        ExternalCharacterResponseDto responseDto = new ExternalCharacterResponseDto(
                new InfoResponseDto(), new ArrayList<>());
        ExternalCharacterResponseDto currentResponseDto;

        try {
            while (nextUrl != null) {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextUrl))
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                currentResponseDto = objectMapper.readValue(response.body(),
                        ExternalCharacterResponseDto.class);
                responseDto.getResults().addAll(currentResponseDto.getResults());
                responseDto.setInfo(currentResponseDto.getInfo());
                nextUrl = currentResponseDto.getInfo().getNext();
            }

            return responseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to get characters from an API", e);
        }
    }
}
