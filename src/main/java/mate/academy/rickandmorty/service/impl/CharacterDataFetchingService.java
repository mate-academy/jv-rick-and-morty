package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.exception.CharacterExternalLoadException;
import mate.academy.rickandmorty.service.DataFetchingService;
import org.springframework.stereotype.Service;

@Service
public class CharacterDataFetchingService implements DataFetchingService<ExternalCharacterDto> {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    private final HttpClient httpClient;

    public CharacterDataFetchingService() {
        httpClient = HttpClient.newHttpClient();
    }

    @Override
    public List<ExternalCharacterDto> fetchAllData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExternalCharacterDto> characterDtoList = new ArrayList<>();
        String nextPageUrl = URL;
        while (nextPageUrl != null) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI(nextPageUrl))
                        .build();
                HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto characterResponseDto =
                        objectMapper.readValue(response.body(), CharacterResponseDto.class);

                characterDtoList.addAll(characterResponseDto.getResults());
                nextPageUrl = characterResponseDto.getInfo().getNext();
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new CharacterExternalLoadException("Can`t fetch data about characters. URL: "
                        + URL, e);
            }
        }
        return characterDtoList;
    }
}
