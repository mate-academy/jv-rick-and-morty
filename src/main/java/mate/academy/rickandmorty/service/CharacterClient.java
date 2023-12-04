package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterService characterService;
    private Long countOfPages;

    public void loadCharactersData() {
        List<CharacterDataDto> characters = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        countOfPages = getCountOfPages(client);        
        HttpRequest request;
        
        for (int i = 1; i <= countOfPages; i++) {
            request = HttpRequest.newBuilder().GET().uri(URI.create(URL)).build();
            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto responseDto = objectMapper.readValue(response.body(),
                        CharacterResponseDto.class);
                
                characters.addAll(responseDto.results());
                URL = responseDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        characterService.saveAll(characters);
    }
    
    private Long getCountOfPages(HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDto responseDto = objectMapper.readValue(response.body(),
                    CharacterResponseDto.class);
            return responseDto.info().pages();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
