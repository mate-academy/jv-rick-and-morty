package mate.academy.rickandmorty.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalResponseDto;
import mate.academy.rickandmorty.dto.external.Result;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    private static final String BASE_CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final PersonageMapper personageMapper;

    public List<Personage> getAllPersonages() {
        List<Result> resultList = new ArrayList<>();
        ExternalResponseDto externalResponseDto = getExternalResponseDto(BASE_CHARACTER_URL);
        while (externalResponseDto.getInfo().getNext() != null) {
            resultList.addAll(externalResponseDto.getResultList());
            externalResponseDto = getExternalResponseDto(externalResponseDto.getInfo().getNext());
        }
        return resultList
                .stream()
                .map(personageMapper::fromExternalResultToPersonage)
                .toList();
    }

    private ExternalResponseDto getExternalResponseDto(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), ExternalResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't send request to url: " + url);
        }
    }
}
