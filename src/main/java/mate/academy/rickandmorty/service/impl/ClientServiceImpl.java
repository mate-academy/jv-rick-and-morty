package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import mate.academy.rickandmorty.model.dto.api.ApiCharacterResponseDto;
import mate.academy.rickandmorty.model.dto.api.ApiResponseDto;
import mate.academy.rickandmorty.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    @SneakyThrows
    private ApiResponseDto getCharacter(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(response.body(), ApiResponseDto.class);
    }

    public List<ApiCharacterResponseDto> getAllCharacters() {
        ApiResponseDto responseDto = getCharacter(URL);
        List<ApiCharacterResponseDto> characters = new ArrayList<>(responseDto.characters());
        while (responseDto.info().next() != null) {
            responseDto = getCharacter(responseDto.info().next());
            characters.addAll(responseDto.characters());
        }
        return characters;
    }
}
