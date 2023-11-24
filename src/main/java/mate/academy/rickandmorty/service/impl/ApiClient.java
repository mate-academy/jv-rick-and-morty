package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiCharacterDataDto;
import mate.academy.rickandmorty.exception.FetchDataFailedException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharactersRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final RestTemplate restTemplate;
    private final CharacterMapper characterMapper;
    private final CharactersRepository charactersRepository;
    private final CharacterService charactersService;

    @PostConstruct
    private void saveAllToDb() {
        String apiUrl = BASE_URL;

        while (apiUrl != null) {
            ApiCharacterDataDto dataDto = fetchData(apiUrl);
            charactersService.saveAll(dataDto.getResults());
            apiUrl = dataDto.getInfo().getNext();
        }
    }

    private ApiCharacterDataDto fetchData(String apiUrl) {
        try {
            return restTemplate.getForObject(apiUrl, ApiCharacterDataDto.class);
        } catch (RestClientException e) {
            throw new FetchDataFailedException("Failed to fetch data from API");
        }
    }
}
