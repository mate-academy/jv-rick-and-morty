package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.PersonageDataDto;
import mate.academy.rickandmorty.dto.external.PersonageResponseDataDto;
import org.springframework.stereotype.Component;

@Component
public class PersonageClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public PersonageClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    public List<PersonageDataDto> fetchPersonages() {
        List<PersonageDataDto> allPersonages = new ArrayList<>();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            if (response != null && response.statusCode() == 200) {
                PersonageResponseDataDto dataDto = objectMapper.readValue(response.body(),
                        PersonageResponseDataDto.class);
                allPersonages.addAll(dataDto.getResults());
            }
            return allPersonages;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to fetch personages from API", e);
        }
    }
}
