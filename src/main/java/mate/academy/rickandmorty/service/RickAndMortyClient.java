package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataResponseDto;
import mate.academy.rickandmorty.exception.BadRequestException;
import mate.academy.rickandmorty.exception.BadResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public CharacterDataResponseDto getAllCharacter(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.value()) {
                return objectMapper.readValue(response.body(), CharacterDataResponseDto.class);
            }
            throw new BadResponseException("Error in response from URL: " + url
                    + ". Status code: " + response.statusCode());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new BadRequestException("Something is wrong with URL: " + url);
        }
    }
}
