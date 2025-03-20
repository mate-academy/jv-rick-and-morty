package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalApiClient {
    private final RestTemplate restTemplate;
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    @Value("${external-api.baseurl}")
    private String baseUrl;
    @Value("${external-api.charactersEndpoint}")
    private String charactersEndpoint;

    public void fetchAndSaveAllCharacters() {
        String requestUrl = baseUrl + charactersEndpoint;
        List<Character> characterEntities = new ArrayList<>();
        while (requestUrl != null) {
            CharacterApiResponseDto response = restTemplate
                    .getForObject(requestUrl, CharacterApiResponseDto.class);
            if (response != null) {
                response.characters()
                        .forEach(c -> characterEntities.add(mapper.toEntity(c)));
                requestUrl = response.info().next();
            } else {
                requestUrl = null;
            }
        }
        repository.saveAll(characterEntities);
    }
}
