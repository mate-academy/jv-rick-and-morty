package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.helper.GetCharactersFromNextPages;
import mate.academy.rickandmorty.client.helper.RandomIndexGenerator;
import mate.academy.rickandmorty.dto.InfoResponseDto;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.dto.RandMResponseList;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Client {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private static final String separatorToNameSearch = "/?name=";

    private static final String separator = "/";

    private final CharacterService characterService;

    private final ObjectMapper mapper;

    private final GetCharactersFromNextPages getCharactersFromNextPages;

    private final RandomIndexGenerator randomIndexGenerator;

    public RandMResponseDto getRickyAndMortyRandomCharacter() {

        String index = randomIndexGenerator.randomIndexOfCharacter();

        List<Integer> allCharactersExternalIds = characterService.findAllExternalId().stream()
                .map(BigDecimal::intValueExact).toList();

        if (allCharactersExternalIds.contains(Integer.parseInt(index))) {
            return characterService.findByExternalId(BigDecimal.valueOf(Integer.parseInt(index)));
        }

        String url = BASE_URL + separator + index;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

        try {

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(),
                        RandMResponseDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void getCharactersWithInputName(String inputName) {

        String inputNameToLowerCase = inputName.toLowerCase();

        HttpClient httpClient = HttpClient.newHttpClient();

        String url = BASE_URL + separatorToNameSearch + inputNameToLowerCase;

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

            List<Integer> allCharacters = characterService.findAllExternalId().stream()
                    .map(BigDecimal::intValueExact).toList();
            List<Integer> list = randMResponseListOnePage.getResults().stream()
                    .map(RandMResponseDto::getId)
                    .map(BigDecimal::intValueExact).toList();

            for (int i = 0; i < randMResponseListOnePage.getResults().size(); i++) {
                if (!(allCharacters.contains(list.get(i)))) {
                    characterService.save(randMResponseListOnePage.getResults().get(i));
                }
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        int pages = infoResponseDto.getInfo().getPages();

        if (pages > 1) {
            getCharactersFromNextPages.getCharactersFromApiWhenMoreThanOnePage(
                    httpClient, inputNameToLowerCase, pages);
        }

        int countOfCharactersInDB = characterService.getAllCharactersWithName(inputName).size();
        int countOfCharactersInRandMapi = infoResponseDto.getInfo().getCount().intValueExact();

        if (!(countOfCharactersInDB == countOfCharactersInRandMapi)) {
            throw new RuntimeException("Number of characters in Rick & Morty API is "
            + countOfCharactersInRandMapi + " and in database "
                    + " is " + countOfCharactersInDB);
        }
    }
}
