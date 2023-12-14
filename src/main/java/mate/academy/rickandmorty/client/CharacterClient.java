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
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseResultDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final int START_PAGE = 1;
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final String URL_WITH_PAGE = "https://rickandmortyapi.com/api/character/?page=%s";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterDto> getAllCharacters() {
        int pages = getCharacterInfo().getPages();
        int page = START_PAGE;
        CharacterResponseResultDto resultDto = null;
        List<CharacterDto> dtos = new ArrayList<>();
        try {
            while (page <= pages) {
                HttpResponse<String> response = httpClient.send(httpRequestByPages(page),
                        HttpResponse.BodyHandlers.ofString());
                resultDto = objectMapper.readValue(response.body(),
                        CharacterResponseResultDto.class);
                dtos.addAll(resultDto.getResults());
                page++;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Characters not found.", e);
        }
        return dtos;
    }

    public CharacterInfoDto getCharacterInfo() {
        CharacterResponseInfoDto infoDto = null;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            infoDto = objectMapper.readValue(response.body(), CharacterResponseInfoDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Character info not found.", e);
        }
        return infoDto.getInfo();
    }

    private HttpRequest httpRequestByPages(int page) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_WITH_PAGE.formatted(page)))
                .build();
    }
}
