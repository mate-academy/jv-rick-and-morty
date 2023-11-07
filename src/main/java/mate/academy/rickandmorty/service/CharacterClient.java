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
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        List<CharacterExternalDto> listDto = new ArrayList<>();
        while (url != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                          .GET()
                                          .uri(URI.create(url))
                                          .build();
            try {
                HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto responseDto = objectMapper.convertValue(httpResponse.body(),
                        CharacterResponseDto.class);
                listDto.addAll(responseDto.getResults());
                url = responseDto.getInfo().getNext();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return listDto;
    }

}
