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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Fetcher {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%d";
    private static final int PAGE_NUMBER = 42;
    private final ObjectMapper objectMapper;

    public List<CharacterResponseDto> getDataFromApi() {
        List<CharacterResponseDto> characterResponseDtoList = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        int currentPage = 1;

        while (currentPage <= PAGE_NUMBER) {
            String url = BASE_URL.formatted(currentPage);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());

                CharacterResponseDataDto responseData = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDataDto.class
                );
                characterResponseDtoList.addAll(0, responseData.results());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentPage++;
        }

        return characterResponseDtoList;
    }
}
