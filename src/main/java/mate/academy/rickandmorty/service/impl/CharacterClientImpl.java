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
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClientImpl implements CharacterClient {
    private final ObjectMapper objectMapper;
    private final List<CharacterResponseDto> characterResponseDtoList = new ArrayList<>();
    @Value("${mate.academy.rickandmorty.url}")
    private String apiUrl;

    @Override
    public List<CharacterResponseDto> getCharactersIntoDB() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharacterInfoDto characterInfoDto = objectMapper
                    .readValue(response.body(), CharacterInfoDto.class);
            int count = Integer.parseInt(characterInfoDto.info().get("count"));
            for (int i = 1; i <= count; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(apiUrl + "/" + i))
                        .build();
                response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                characterResponseDtoList.add(objectMapper.readValue(response.body(),
                        CharacterResponseDto.class));
            }
            return characterResponseDtoList;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get character data from API.",e);
        }
    }
}
