package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.helper.GetCharactersFromNextPages;
import mate.academy.rickandmorty.dto.InfoResponseDto;
import mate.academy.rickandmorty.dto.RandMResponseList;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Client {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final CharacterService characterService;

    private final ObjectMapper mapper;

    private final GetCharactersFromNextPages getCharactersFromNextPages;

    public void getRickyAndMortyAllCharacters() {

        HttpClient httpClient = HttpClient.newHttpClient();

        String url = BASE_URL;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        InfoResponseDto infoResponseDto = null;
        try {

            infoResponseDto = mapper.readValue(response.body(), InfoResponseDto.class);

            RandMResponseList randMResponseListOnePage = mapper.readValue(response.body(),
                    RandMResponseList.class);


            for (int i = 0; i < randMResponseListOnePage.getResults().size(); i++) {
                    characterService.save(randMResponseListOnePage.getResults().get(i));
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        int pages = infoResponseDto.getInfo().getPages();

        getCharactersFromNextPages.getCharactersFromNextPage(
                    httpClient, pages);

        int countOfCharactersInDB = characterService.getNumberOfCharactersInDb();
        int countOfCharactersInRandMapi = infoResponseDto.getInfo().getCount().intValueExact();

        if (!(countOfCharactersInDB == countOfCharactersInRandMapi)) {
            throw new RuntimeException("Number of characters in Rick & Morty API is "
            + countOfCharactersInRandMapi + " and in database "
                    + " is " + countOfCharactersInDB);
        }
    }
}
