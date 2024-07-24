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
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    @Value("${mate.academy.base_url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;

    public List<CharacterDataDto> getCharactersFromDb() {
        String url = baseUrl;
        List<CharacterDataDto> characterList = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto responseDataDto =
                        objectMapper.readValue(
                                response.body(),
                                CharacterResponseDataDto.class);
                characterList.addAll(responseDataDto.getData());
                url = responseDataDto.getInfo().next();
            }
            return characterList;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
