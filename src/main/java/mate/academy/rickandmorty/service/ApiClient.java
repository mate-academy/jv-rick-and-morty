package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void getAllCharactersToDb() {
        int currentPage = 1;
        int totalPages;

        do {
            String nextPageUrl = BASE_URL + "/character?page=" + currentPage;
            CharacterResponseDataDto dataDto = fetchDataFromUrl(nextPageUrl);
            saveEachCharacterToDb(dataDto.getResults());

            totalPages = dataDto.getInfo().getPages();
            currentPage++;
        } while (currentPage <= totalPages);
    }

    private void saveEachCharacterToDb(List<CharacterDto> results) {

        List<Character> list = results.stream()
                .map(characterMapper::toModel)
                .toList();

        characterRepository.saveAll(list);
    }

    private CharacterResponseDataDto fetchDataFromUrl(String nextPageUrl) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(nextPageUrl))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
