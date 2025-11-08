package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.model.CharacterRM;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        String url = BASE_URL;
        List<CharacterRM> allCharacters = new ArrayList<>();

        while (url != null) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String jsonResponse = responseEntity.getBody();

            CharacterResponse response = objectMapper.readValue(jsonResponse, CharacterResponse.class);
            allCharacters.addAll(response.results());

            url = response.info().next();
        }

        characterRepository.saveAll(allCharacters);
        System.out.println("✅ Total characters saved: " + allCharacters.size());
    }
}
