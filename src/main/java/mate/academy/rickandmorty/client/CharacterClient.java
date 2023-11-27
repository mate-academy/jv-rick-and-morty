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
import mate.academy.rickandmorty.dto.external.CharacterApiInfo;
import mate.academy.rickandmorty.dto.external.CharacterResponse;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private static final String BASE_URL_TO_GET_INFO = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public CharacterApiInfo getInfoAboutApi() {
        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(BASE_URL_TO_GET_INFO)).build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterApiInfo.class);
        } catch (IOException | RuntimeException | InterruptedException e) {
            throw new EntityNotFoundException("Can't get info about external api");
        }
    }

    public List<Character> getAllCharacters() {
        CharacterApiInfo characterApiInfo = getInfoAboutApi();
        List<CharacterResponse> characterRequestList = new ArrayList<>();
        int index = 1;
        while (index <= characterApiInfo.getInfo().getPages()) {
            try {
                addHttpResponseToList(characterRequestList, index);
                index++;
            } catch (IOException | InterruptedException e) {
                throw new EntityNotFoundException("Can't get all characters " + e);
            }
        }
        return characterRequestList.stream()
                .flatMap(c -> c.getResults()
                        .stream())
                .map(characterMapper::toModel).toList();
    }

    private HttpRequest getHttpRequest(int index) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL.formatted(index)))
                .build();
    }

    private void addHttpResponseToList(List<CharacterResponse> list, int index)
            throws IOException, InterruptedException {
        HttpResponse<String> httpResponse
                = httpClient.send(getHttpRequest(index),
                HttpResponse.BodyHandlers.ofString());
        CharacterResponse characterRequest =
                objectMapper.readValue(httpResponse.body(),
                        CharacterResponse.class);
        list.add(characterRequest);
    }
}
