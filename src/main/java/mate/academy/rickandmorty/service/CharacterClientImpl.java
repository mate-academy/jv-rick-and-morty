package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterClientImpl implements CharacterClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final RestTemplate restTemplate;

    public CharacterClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CharacterResultDto> getAllCharacters() {
        List<CharacterResultDto> result = new ArrayList<>();
        String url = BASE_URL;
        CharacterResponseDataDto response;
        do {
            response = restTemplate.getForObject(url, CharacterResponseDataDto.class);
            if (response != null && response.getCharacterResultDto() != null) {
                url = response.getCharacterInfoDto().getNext();
                result.addAll(response.getCharacterResultDto());
            }
        } while (response != null && response.getCharacterInfoDto().getNext() != null);
        return result;
    }
}
