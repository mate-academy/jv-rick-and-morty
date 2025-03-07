package mate.academy.rickandmorty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dao.CharacterToCreateDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner {

    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            CharacterResponse characterResponse = objectMapper
                    .readValue(response.body(), CharacterResponse.class);

            List<CharacterToCreateDto> characterToCreateDtos = characterResponse.getResults();

            List<Character> characters = characterToCreateDtos.stream()
                    .map(i -> new Character(
                            String.valueOf(i.getId()),
                            i.getName(),
                            i.getStatus(),
                            i.getGender()))
                    .toList();
            characterRepository.saveAll(characters);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CharacterResponse {
        private List<CharacterToCreateDto> results;
    }

}
