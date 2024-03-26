package mate.academy.rickandmorty.service;

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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyClientService {
    private static String URL = "https://rickandmortyapi.com/api/character";
    private static final String RESULT_LIST = "results";
    private static final String INFO = "info";
    private static final String NEXT_PAGE = "next";

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterRequestDto> getCharacters() {
        List<CharacterRequestDto> characterRequestDtos = new ArrayList<>();
        try {
            String initResponse;
            JsonNode jsonNode;
            JsonNode results;
            String url = URL;
            do {
                initResponse = fetchAllCharacters(url);
                jsonNode = objectMapper.readTree(initResponse);
                results = jsonNode.get(RESULT_LIST);
                List<CharacterRequestDto> result = objectMapper.readValue(
                        results.toString(),
                        new TypeReference<>() {});
                characterRequestDtos.addAll(result);
                url = getNextPage(jsonNode).asText();
            } while (hasNextPage(jsonNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return characterRequestDtos;
    }

    private JsonNode getNextPage(JsonNode jsonNode) {
        return jsonNode.get(INFO).get(NEXT_PAGE);
    }

    private boolean hasNextPage(JsonNode jsonNode) {
        return !jsonNode.get(INFO).get(NEXT_PAGE).isNull();
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
}
