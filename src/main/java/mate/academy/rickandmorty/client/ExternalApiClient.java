package mate.academy.rickandmorty.client;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ExternalApiClient {
    private final RestTemplate restTemplate;
    @Value("${rick.api.base-url}")
    private String baseUrl;

    public List<ExternalCharacterDto> fetchAllCharacters() {
        List<ExternalCharacterDto> allCharacters = new ArrayList<>();
        int page = 1;

        while (true) {
            String url = baseUrl + "?page=" + page;
            try {
                CharacterResponseDto response = restTemplate.getForObject(url, CharacterResponseDto.class);
                if (response == null || response.results() == null || response.results().isEmpty()) {
                    break;
                }
                allCharacters.addAll(response.results());
                page++;
            } catch (HttpClientErrorException.NotFound ex) {
                break;
            }
        }
        return allCharacters;
    }
}
