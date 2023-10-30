package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.CharacterResponseDtoList;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${rick-and-morty-url}")
    private String url;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate;

    public void getCharacters() {
        try {
            CharacterResponseDtoList characterResponseDtoList
                    = restTemplate.getForObject(url, CharacterResponseDtoList.class);
            ArrayList<CharacterResponseDto> characters
                    = new ArrayList<>(characterResponseDtoList.results());
            String nextUrl = characterResponseDtoList.info().next();
            while (nextUrl != null) {
                characterResponseDtoList
                        = restTemplate.getForObject(nextUrl, CharacterResponseDtoList.class);
                characters.addAll(characterResponseDtoList.results());
                nextUrl = characterResponseDtoList.info().next();
            }
            characterRepository.saveAll(characters.stream()
                    .map(characterMapper::toCharacter)
                    .toList());
        } catch (Exception e) {
            throw new DataProcessingException("Can't load characters");
        }
    }
}
