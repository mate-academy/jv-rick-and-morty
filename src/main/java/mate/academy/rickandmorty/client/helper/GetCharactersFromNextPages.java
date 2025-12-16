package mate.academy.rickandmorty.client.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RandMResponseList;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCharactersFromNextPages {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private static final String PAGE_INDEX = "/?page=";

    private final ObjectMapper mapper;

    private final CharacterService characterService;

    public void getCharactersFromNextPage(
            HttpClient httpClient, int pages) {

        for (int i = 2; i <= pages; i++) {
            String urlWithPage = BASE_URL + PAGE_INDEX + i;
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


                for (int j = 0; j < randMResponseList.getResults().size(); j++) {
                        characterService.save(randMResponseList.getResults().get(j));
                    }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
