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
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    private final CharacterMapper characterMapper;

    private final CharacterRepository characterRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeDb();
    }

    public void initializeDb() {
        if (characterRepository.count() > 0) {
            return;
        }
        HttpClient httpClient = HttpClient.newHttpClient();
        CharacterResponseDataDto characterResponseDataDto = null;
        do {
            String uri = (characterResponseDataDto == null)
                    ? URL : characterResponseDataDto.info().next();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                characterResponseDataDto = objectMapper.readValue(response.body(),
                        CharacterResponseDataDto.class);
                List<Character> characters = new ArrayList<>();
                characterResponseDataDto.results().forEach(
                        (i) -> characters
                                .add(characterMapper.toModel(i)));
                characterRepository.saveAll(characters);
                Thread.sleep(300);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (characterResponseDataDto.info().next() != null);
    }
}
