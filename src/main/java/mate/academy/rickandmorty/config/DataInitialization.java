package mate.academy.rickandmorty.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitialization {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void initData() {
        if (characterRepository.count() > 0) {
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://rickandmortyapi.com/api/character";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            CharacterResponse characterResponse = objectMapper.readValue(response.body(),
                    CharacterResponse.class);
            List<CharacterDto> characterList = characterResponse.getResults();

            characterRepository.saveAll(characterMapper.toEntityList(characterList));
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch data from API: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Request to API was interrupted: " + e.getMessage(), e);
        }
    }
}
