package mate.academy.rickandmorty.external.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFullResponseDto;
import mate.academy.rickandmorty.exeption.ConnectionException;
import mate.academy.rickandmorty.exeption.ConvertException;
import mate.academy.rickandmorty.external.api.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharactersClientImpl implements CharacterClient {
    private final ObjectMapper objectMapper;

    public CharacterFullResponseDto getDtoFromApi(String url) {
        HttpResponse<String> response;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException("Can't get response from: " + url, e);
        }

        return convertResponse(response);
    }

    private CharacterFullResponseDto convertResponse(HttpResponse<String> response) {
        try {
            return objectMapper.readValue(response.body(),
                    CharacterFullResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ConvertException(
                    "Can't convert response to CharacterFullResponseDto.class", e);
        }
    }
}
