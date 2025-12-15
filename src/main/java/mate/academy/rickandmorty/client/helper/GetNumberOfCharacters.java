package mate.academy.rickandmorty.client.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.InfoResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNumberOfCharacters {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper mapper;

    public int getInfoAboutCountOfCharacters() {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest countRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build(); // https://rickandmortyapi.com/api/character

        HttpResponse<String> countResponse = null;

        InfoResponseDto infoCountResponseDto = null;
        try {
            countResponse = httpClient.send(countRequest,
                    HttpResponse.BodyHandlers.ofString());

            infoCountResponseDto = mapper.readValue(
                    countResponse.body(), InfoResponseDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return infoCountResponseDto.getInfo().getCount().intValueExact();
    }
}
