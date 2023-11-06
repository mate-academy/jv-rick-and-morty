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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";

    private final ObjectMapper objectMapper;

    private final CharacterMapper characterMapper;

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();

        CharacterResponseDataDto firstPage = getDataByPage(1);
        int amountOfpages = firstPage.getInfo().getPages();
        addMappedCharactersToList(characters, firstPage);

        for (int i = 2; i <= amountOfpages; i++) {
            CharacterResponseDataDto data = getDataByPage(i);
            addMappedCharactersToList(characters, data);
        }
        return characters;
    }

    public CharacterResponseDataDto getDataByPage(int page) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL.formatted(page)))
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto responseDto = 
                    objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
            return responseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Was unable to send request", e);
        }
    }

    private void addMappedCharactersToList(List<Character> to,
            CharacterResponseDataDto data) {
        to.addAll(data.getCharacters().stream()
                .map(characterMapper::fromExternalDto)
                .toList());
    }
}
