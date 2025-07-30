package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.RickAndMortyResponseDto;
import mate.academy.rickandmorty.exception.HttpSendException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper mapper;
    private final CharacterRepository repository;
    private final CharacterMapper characterMapper;

    public List<Character> downloadCharacters() {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            RickAndMortyResponseDto responseDto = mapper.readValue(
                    response.body(),
                    RickAndMortyResponseDto.class);

            System.out.println(response.body());

            List<CharacterResponseDto> listCharacterDto = responseDto.getResults().stream().toList();

            List<Character> list = listCharacterDto.stream()
                    .map(characterMapper::toCharacter)
                    .toList();

            return repository.saveAll(list);
        } catch (IOException | InterruptedException e) {
            throw new HttpSendException("Cant send request to RickAndMorty API");
        }
    }

    @PostConstruct
    public void init() {
        downloadCharacters();
    }
}
