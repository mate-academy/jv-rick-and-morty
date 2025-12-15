package mate.academy.rickandmorty.client.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.dto.RandMResponseList;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCharactersFromNextPages {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private static final String separatorToNameSearch = "/?name=";

    private static final String PAGE_INDEX = "&page=";

    private final ObjectMapper mapper;

    private final CharacterService characterService;

    public void getCharactersFromApiWhenMoreThanOnePage(
            HttpClient httpClient, String name, int pages) {
        for (int i = 2; i <= pages; i++) {
            String urlWithPage = BASE_URL + separatorToNameSearch + name
                    + PAGE_INDEX + i;
            HttpRequest httpRequestWithPage = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlWithPage))
                    .build();

            try {
                HttpResponse<String> responseWithPage = httpClient.send(httpRequestWithPage,
                        HttpResponse.BodyHandlers.ofString());
                RandMResponseList randMResponseList = null;
                randMResponseList = mapper.readValue(
                        responseWithPage.body(), RandMResponseList.class);

                List<Integer> allCharactersExternalIds =
                        characterService.findAllExternalId().stream()
                        .map(BigDecimal::intValueExact).toList();
                List<Integer> list = randMResponseList.getResults().stream().map(
                                RandMResponseDto::getId)
                        .map(BigDecimal::intValueExact).toList();

                for (int j = 0; j < list.size(); j++) {
                    if (!(allCharactersExternalIds.contains(list.get(j)))) {
                        characterService.save(randMResponseList.getResults().get(j));
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
