package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CartoonCharactersListResponseDto;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import mate.academy.rickandmorty.exceptions.GettingCharactersListException;
import mate.academy.rickandmorty.service.CartoonCharacterClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartoonCharacterClientImpl implements CartoonCharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    @Override
    public List<CreateCartoonCharacterRequestDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        List<CreateCartoonCharacterRequestDto> result = new ArrayList<>();
        while (url != null) {
            CartoonCharactersListResponseDto cartoonCharactersListResponseDto =
                    nextPage(httpClient, url);
            url = cartoonCharactersListResponseDto.info().next();
            result.addAll(cartoonCharactersListResponseDto.results());
        }
        return result;
    }

    private CartoonCharactersListResponseDto nextPage(HttpClient httpClient, String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(),
                            CartoonCharactersListResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new GettingCharactersListException("Error getting characters list, url: "
                    + url, e);
        }
    }
}
