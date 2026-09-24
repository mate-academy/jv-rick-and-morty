package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RickMortyClient {

    private final RestTemplate restTemplate;

    public CharacterResponseDto getCharacters(int page) {
        return restTemplate.getForObject(
                "https://rickandmortyapi.com/api/character?page=" + page,
                CharacterResponseDto.class);
    }
}
