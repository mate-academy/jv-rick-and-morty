package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.exception.RetrieveDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    @Value("${rick-and-morty.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public List<CharacterInfoDto> loadCharacters() {
        try {
            var characterResponseDto = restTemplate
                    .getForObject(apiUrl, CharacterResponseDto.class);
            var characters = new ArrayList<>(characterResponseDto.results());
            String nextPageUrl = characterResponseDto.info().next();
            while (nextPageUrl != null) {
                characterResponseDto = restTemplate
                        .getForObject(nextPageUrl, CharacterResponseDto.class);
                characters.addAll(characterResponseDto.results());
                nextPageUrl = characterResponseDto.info().next();
            }
            return characters;
        } catch (Exception e) {
            throw new RetrieveDataException("Retrieving characters failed", e);
        }
    }
}
