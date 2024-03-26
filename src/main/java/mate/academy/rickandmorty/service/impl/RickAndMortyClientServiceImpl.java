package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
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
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.service.RickAndMortyClientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyClientServiceImpl implements RickAndMortyClientService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private static final String RESULT_LIST = "results";
    private static final String INFO = "info";
    private static final String NEXT_PAGE = "next";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterRequestDto> getCharacters() {
        List<CharacterRequestDto> characterRequestDtos = new ArrayList<>();
        try {
            JsonNode jsonNode;
            JsonNode results;
            String url = URL;
            do {
                String initResponse = fetchAllCharacters(url);
                jsonNode = objectMapper.readTree(initResponse);
                results = jsonNode.get(RESULT_LIST);
                List<CharacterRequestDto> result = objectMapper.readValue(results.toString(),
                        new TypeReference<>() {});
                characterRequestDtos.addAll(result);
                url = getNextPage(jsonNode);
            } while (hasNextPage(jsonNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return characterRequestDtos;
    }

    private String fetchAllCharacters(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                                             .GET()
                                             .uri(new URI(url))
                                             .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNextPage(JsonNode jsonNode) {
        return jsonNode.get(INFO)
                       .get(NEXT_PAGE)
                       .asText();
    }

    private boolean hasNextPage(JsonNode jsonNode) {
        return !jsonNode.get(INFO).get(NEXT_PAGE).isNull();
    }
}
