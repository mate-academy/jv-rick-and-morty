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
import mate.academy.rickandmorty.dto.external.ExternalCharacterResponseDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.exception.CharacterDataRetrievalInterruptedException;
import mate.academy.rickandmorty.exception.CharacterDataRetrievalIoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExternalClient {
    @Value("${rick.and.morty.external.url}")
    private String url;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<Character> getAllCharacters() {
        List<Character> allCharacters = new ArrayList<>();
        String nextUrl = url;

        while (nextUrl != null && !nextUrl.isBlank()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );
                ExternalCharacterResponseDto pageData = objectMapper.readValue(
                        response.body(),
                        ExternalCharacterResponseDto.class
                );

                allCharacters.addAll(pageData.getResults());
                nextUrl = pageData.getInfo().getNext();
            } catch (IOException e) {
                throw new CharacterDataRetrievalIoException("Problem with the network or I/O", e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CharacterDataRetrievalInterruptedException(
                        "The request was interrupted", e);
            }
        }
        return allCharacters;
    }
}
