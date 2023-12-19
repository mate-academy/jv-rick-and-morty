package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataLoader {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    public void loadData() {
        List<CharacterDto> data = getExternalData();
        saveData(data);
    }

    public List<CharacterDto> getExternalData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(BASE_URL))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = objectMapper.readTree(response.body());
            JsonNode resultJson = jsonNode.get("results");

            List<CharacterDto> responseDataDtos = objectMapper.convertValue(resultJson,
                    new TypeReference<List<CharacterDto>>() {
                    }
            );
            return responseDataDtos;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveData(List<CharacterDto> data) {
        for (CharacterDto dto : data) {
            characterRepository.save(characterMapper.toEntity(dto));
        }
    }
}
