package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.model.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.model.external.CharacterResultDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoadOnStartup {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public DataLoadOnStartup(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterResultDto> getDataByApi() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto = objectMapper.readValue(response.body(),
                    CharacterResponseDataDto.class);
            return dataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
