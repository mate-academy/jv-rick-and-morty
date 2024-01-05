package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.exception.CharacterExternalLoadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${rick-and-morty.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    public List<CharacterExternalDto> getAllCharacters() {
        String currentUrl = baseUrl;
        List<CharacterExternalDto> result = new ArrayList<>();
        try {
            while (currentUrl != null) {
                var pageResult = restTemplate.getForObject(currentUrl, CharacterResponseDto.class);
                assert pageResult != null;
                result.addAll(pageResult.results());
                currentUrl = (String) pageResult.info().get("next");
            }
        } catch (Exception e) {
            throw new CharacterExternalLoadException("Can't load character", e);
        }
        return result;
    }
}
