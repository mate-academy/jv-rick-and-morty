package mate.academy.rickandmorty.service.watcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.InfoResponseDto;
import mate.academy.rickandmorty.exception.CharacterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private final ObjectMapper objectMapper;
    @Value("${mate.academy.animation.outer.db}")
    private String baseUrl;

    public List<CharacterResponseDto> getListOfAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        List<CharacterResponseDto> allCharacters = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl))
                .build();

        String nextUrl = getInfoResponseDto(httpClient, request)
                .getInfo()
                .next();

        return getRestOfCharactersOnOtherPages(
                nextUrl,
                httpClient,
                allCharacters);
    }

    private List<CharacterResponseDto> getRestOfCharactersOnOtherPages(
            String nextUrl,
            HttpClient httpClient,
            List<CharacterResponseDto> allCharacters) {

        HttpResponse<String> response;
        InfoResponseDto infoResponseDto;

        try {
            while (nextUrl != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextUrl))
                        .build();

                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                infoResponseDto = objectMapper.readValue(response.body(), InfoResponseDto.class);

                allCharacters.addAll(infoResponseDto.getResults());
                nextUrl = infoResponseDto.getInfo().next();
            }
            return allCharacters;
        } catch (IOException | InterruptedException e) {
            throw new CharacterException(
                    "Error in taking character from other pages", e);
        }
    }

    private InfoResponseDto getInfoResponseDto(HttpClient httpClient, HttpRequest request) {
        HttpResponse<String> response;
        InfoResponseDto infoResponseDto;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            infoResponseDto = objectMapper.readValue(response.body(), InfoResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new CharacterException("Something go wrong when u try "
                    + "take character from first page", e);
        }
        return infoResponseDto;
    }
}
