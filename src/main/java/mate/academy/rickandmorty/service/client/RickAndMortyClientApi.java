package mate.academy.rickandmorty.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Tag(
        name = "Rick And Morty Client API",
        description = "This class is responsible for interacting with the"
                + " Rick and Morty API to retrieve character data")
@Component
@RequiredArgsConstructor
public class RickAndMortyClientApi {
    @Value("${basic.api.url}")
    private String url;
    private final ObjectMapper objectMapper;

    public List<CharacterResultDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto = objectMapper.readValue(response.body(),
                    CharacterResponseDataDto.class);
            return dataDto.getResults().stream().toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching "
                    + "character data from the Rick and Morty API",e);
        }
    }
}
