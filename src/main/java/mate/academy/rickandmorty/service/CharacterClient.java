package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exception.RetrieveDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${mate.academy.rick-and-morty.api.character.url}")
    private static String apiUrl;
    //private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<CharacterInfoDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto characterResponseDataDto = objectMapper
                    .readValue(response.body(), CharacterResponseDataDto.class);
            return characterResponseDataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*try {
            CharacterResponseDataDto characterResponseDataDto = restTemplate
                    .getForObject(apiUrl, CharacterResponseDataDto.class);
            List<CharacterInfoDto> characterInfoDtoList = characterResponseDataDto.getResults();
            String nextPage = characterResponseDataDto.getCharacterMetaDataDto().next();
            while (nextPage != null) {
                characterResponseDataDto = restTemplate
                    .getForObject(nextPage, CharacterResponseDataDto.class);
                characterInfoDtoList.addAll(characterResponseDataDto.getResults());
                nextPage = characterResponseDataDto.getCharacterMetaDataDto().next();
            }
            return characterInfoDtoList;
        } catch (Exception e) {
            throw new RetrieveDataException("Failed to get characters' data", e);
        }*/
    }
}
