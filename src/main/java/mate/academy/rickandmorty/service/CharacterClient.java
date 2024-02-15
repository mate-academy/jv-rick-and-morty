package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.dto.api.CharactersResponseDataDto;
import mate.academy.rickandmorty.dto.api.SingleCharacterDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%d";
    private static final int ORIGIN_PAGE = 1;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    @SneakyThrows
    public List<CreateCharacterRequestDto> getRequestDtos() {
        int page = ORIGIN_PAGE;
        List<SingleCharacterDataDto> singleCharacterDataDtos
                = new ArrayList<>();
        String url;
        HttpClient httpClient = HttpClient.newHttpClient();
        CharactersResponseDataDto dataDto;
            do {
                url = BASE_URL.formatted(page);
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = httpClient
                        .send(httpRequest,
                                HttpResponse.BodyHandlers.ofString());
                dataDto = objectMapper
                        .readValue(response.body(),
                                CharactersResponseDataDto.class);
                singleCharacterDataDtos.addAll(Arrays
                        .stream(dataDto.getResults())
                        .toList());
                page++;
            } while (dataDto.getInfo().getNext() != null);
            return singleCharacterDataDtos.stream()
                    .map(characterMapper::toRequestDto)
                    .toList();
    }
}
