package mate.academy.rickandmorty.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exeption.CharacterClientException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private final ObjectMapper objectMapper;

    public CharacterResponseDataDto getAllCharacters(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new CharacterClientException(String.format(
                        "Failed to fetch data. HTTP status: %d, Response: %s",
                        response.statusCode(),
                        response.body()
                ));
            }
            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new CharacterClientException(
                    "Error occurred while fetching data from URL: " + url, e);
        }
    }
}
