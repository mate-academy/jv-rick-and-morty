package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CharactersClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<CharacterResponseDataDto> getAllCharacters() {
        List<CharacterResponseDataDto> allCharacters = new ArrayList<>();
        String nextUrl = BASE_URL;

        while (nextUrl != null && !nextUrl.isEmpty()) {
            ApiResponseDto response = restTemplate.getForObject(nextUrl, ApiResponseDto.class);
            if (response != null && response.getResults() != null) {
                allCharacters.addAll(response.getResults());
                nextUrl = response.getInfo().getNext();
            } else {
                break;
            }
        }
        return allCharacters;
    }
}
