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
import mate.academy.rickandmorty.dto.external.ExternalCharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    @SneakyThrows
    private ExternalResponseDto getCharacter(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(response.body(), ExternalResponseDto.class);
    }

    public List<ExternalCharacterResponseDto> getAllCharacters() {
        ExternalResponseDto responseDto = getCharacter(URL);
        List<ExternalCharacterResponseDto> characters = new ArrayList<>(responseDto.results());
        while (responseDto.info().next() != null) {
            responseDto = getCharacter(responseDto.info().next());
            characters.addAll(responseDto.results());
        }
        return characters;
    }
}
