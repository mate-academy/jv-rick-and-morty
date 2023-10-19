package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character?page=%s";
    private final ObjectMapper objectMapper;
    
    public List<CharacterInfoDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        
        List<CharacterInfoDto> dtosList = new ArrayList<>();
        for (int i = 0; true; i++) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                              .GET()
                                              .uri(URI.create(String.format(URL, i)))
                                              .build();
            try {
                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String results =
                        objectMapper.readTree(response.body()).get("results").toString();
                CharacterInfoDto[] characterInfoDtos =
                        objectMapper.readValue(results, CharacterInfoDto[].class);
                dtosList.addAll(Arrays.asList(characterInfoDtos));
            } catch (NullPointerException e) {
                break;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return dtosList;
    }
}
