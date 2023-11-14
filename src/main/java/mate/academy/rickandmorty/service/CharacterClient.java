package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.config.CharacterMapper;
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    private void getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        List<CharacterExternalDto> listDto = new ArrayList<>();
        while (url != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                          .GET()
                                          .uri(URI.create(url))
                                          .build();
            try {
                HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto responseDto = objectMapper.readValue(httpResponse.body(),
                        CharacterResponseDto.class);
                listDto.addAll(responseDto.getResults());
                url = responseDto.getInfo().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        saveAllCharacter(listDto);
    }

    private void saveAllCharacter(List<CharacterExternalDto> listDto) {
        characterRepository.deleteAll();
        characterRepository.saveAll(listDto.stream()
                                        .map(characterMapper::mapToCharacter)
                                        .toList());
    }

}
