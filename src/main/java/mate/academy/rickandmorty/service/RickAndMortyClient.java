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
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.external.ListOfCharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public ListOfCharacterResponseDto fetchPage(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return objectMapper.readValue(body, ListOfCharacterResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Cannot send request on " + url);
        }
    }

    public List<CharacterDto> fetchAllPages() {
        String url = URL;
        List<CharacterDto> accum = new ArrayList<>();

        do {
            ListOfCharacterResponseDto fetchedPage = fetchPage(url);

            url = fetchedPage.getInfo().getNext();
            accum.addAll(fetchedPage.getCharacters());
        } while (url != null);

        return accum;
    }

}
