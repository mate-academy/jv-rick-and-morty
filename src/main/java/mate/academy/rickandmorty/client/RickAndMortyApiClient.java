package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import mate.academy.rickandmorty.dto.external.CharactersResponseDataDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyApiClient {
    private static final String PAGEABLE_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterResponseInfoDto> getAllCharacters() {
        int pageCounter = 1;
        CharactersResponseDataDto firstPageCharacterData = getCharactersDataByPage(pageCounter);
        List<CharacterResponseInfoDto> characters = new ArrayList<>(
                firstPageCharacterData.getResults()
        );

        while (pageCounter < firstPageCharacterData.getInfo().getPages()) {
            pageCounter++;

            CharactersResponseDataDto charactersDataByPage = getCharactersDataByPage(pageCounter);
            characters.addAll(charactersDataByPage.getResults());
        }

        return characters;
    }

    private CharactersResponseDataDto getCharactersDataByPage(int pageNumber) {
        String url = PAGEABLE_CHARACTERS_URL.formatted(pageNumber);

        HttpRequest getBasicInfoRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> basicInfoResponse = httpClient.send(
                    getBasicInfoRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            return objectMapper.readValue(
                    basicInfoResponse.body(),
                    CharactersResponseDataDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get info about characters on page: " + pageNumber, e);
        }
    }
}
