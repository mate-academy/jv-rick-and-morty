package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterClientDto;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${rick.and.morty.api.url}")
    private String rickAndMortyApiUrl;
    private final ObjectMapper mapper;

    public List<CharacterClientDto> getCharacters() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(rickAndMortyApiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto responseDataDto = mapper.readValue(response.body(),
                    CharacterResponseDataDto.class);
            System.out.println(responseDataDto);
            return responseDataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
