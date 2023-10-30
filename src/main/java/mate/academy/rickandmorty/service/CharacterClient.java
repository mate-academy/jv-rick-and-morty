package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDtoList;
import mate.academy.rickandmorty.dto.MetaInfo;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public void getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            MetaInfo metaInfo = objectMapper.readValue(response.body(), MetaInfo.class);
            int countOfPages = Integer.parseInt(metaInfo.info().get("pages"));
            for (int i = 1; i <= countOfPages; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(URL + "/?page=" + i))
                        .build();
                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                List<Character> list = objectMapper
                        .readValue(response.body(), CharacterResponseDtoList.class)
                        .results().stream()
                        .map(characterMapper::toEntity)
                        .toList();
                list.forEach(c -> c.setExternalId(c.getId()));
                characterRepository.saveAll(list);
            }
        } catch (Exception e) {
            throw new DataProcessingException("Can't load characters");
        }
    }
}
