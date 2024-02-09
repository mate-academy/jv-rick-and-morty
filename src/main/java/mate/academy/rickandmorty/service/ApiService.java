package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.PageResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private static final String URL_PAGE_PARAM = "?page=";
    private final CharacterMapper characterMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<Character> fetchDataFromApi() {
        List<Character> resultList = new ArrayList<>();
        int pageNumber = 1;
        while (true) {
            String url = API_URL + URL_PAGE_PARAM + pageNumber;
            PageResponseDto pageResponseDto =
                    patchApiData(url);
            List<Character> characters = pageResponseDto.getResults()
                    .stream()
                    .map(characterMapper::toModel)
                    .toList();
            resultList.addAll(characters);
            pageNumber++;
            if (pageResponseDto.getInfo().getNext() == null) {
                break;
            }
        }
        return resultList;
    }

    private PageResponseDto patchApiData(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), PageResponseDto.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
