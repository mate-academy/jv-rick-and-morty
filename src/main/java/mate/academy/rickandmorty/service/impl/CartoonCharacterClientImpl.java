package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%s";
    private static final Long PAGES_COUNT = 1L;
    private final ObjectMapper objectMapper;

    @Override
    public List<CreateCartoonCharacterRequestDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            Long pagesCount = PAGES_COUNT;
            List<CreateCartoonCharacterRequestDto> result = null;
            for (int i = 0; i < pagesCount; i++) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(BASE_URL.formatted(i)))
                        .build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CartoonCharactersListResponseDto cartoonCharactersListResponseDto =
                        objectMapper.readValue(response.body(),
                                CartoonCharactersListResponseDto.class);
                pagesCount = cartoonCharactersListResponseDto.info().pages();
                if (result == null) {
                    result = cartoonCharactersListResponseDto.results();
                } else {
                    result.addAll(cartoonCharactersListResponseDto.results());
                }
            }
            if (result == null || result.isEmpty()) {
                throw new GettingCharactersListException("Error getting characters list");
            }
            return result;
        } catch (IOException | InterruptedException e) {
            throw new GettingCharactersListException("Error getting characters list", e);
        }
    }
}
