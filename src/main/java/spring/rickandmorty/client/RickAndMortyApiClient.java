package spring.rickandmorty.client;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.rickandmorty.dto.ExternalCharacterDto;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {

    private final RestTemplate restTemplate;

    public List<ExternalCharacterDto> getAllCharacters() {
        String url = "https://rickandmortyapi.com/api/character";
        RickAndMortyApiResponse response = restTemplate.getForObject(url,
                RickAndMortyApiResponse.class);
        if (response != null && response.getResults() != null) {
            return response.getResults();
        } else {
            return List.of();
        }
    }
}
