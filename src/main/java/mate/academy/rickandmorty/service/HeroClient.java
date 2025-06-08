package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.HeroDto;
import mate.academy.rickandmorty.dto.external.HeroResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HeroClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    public List<HeroDto> getHeroes() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            HeroResponseDto responseDto = objectMapper.readValue(
                    response.body(),
                    HeroResponseDto.class);
            return responseDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
