package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    @Qualifier("characterMapperImpl")
    private final CharacterMapper mapper;

    public List<Character> getAll() throws IOException {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode resultsNode;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(URL))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            JsonNode rootNode = objectMapper.readTree(response.body());
            resultsNode = rootNode.get("results");
        } catch (IOException | InterruptedException e) {
            throw new IOException("Failed to fetch characters from external API", e);
        }

        List<ExternalCharacterDto> externalCharacters = objectMapper.readValue(
                resultsNode.traverse(),
                new TypeReference<>() {
                }
        );

        return externalCharacters.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
    }
}
