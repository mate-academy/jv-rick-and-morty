package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiResponseDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void run(String... args) throws Exception {
        if (characterRepository.count() == 0) {
            ApiResponseDto response = restTemplate.getForObject(
                    "https://rickandmortyapi.com/api/character", ApiResponseDto.class);
            List<Character> characterList = response.getResults()
                    .stream()
                    .map(characterMapper::toEntity)
                    .toList();
            characterRepository.saveAll(characterList);
            while (response.getInfo().getNext() != null) {
                response = restTemplate.getForObject(
                        response.getInfo().getNext(), ApiResponseDto.class);
                List<Character> nextCharacterList = response.getResults()
                        .stream()
                        .map(characterMapper::toEntity)
                        .toList();
                characterRepository.saveAll(nextCharacterList);
                Thread.sleep(500);
            }
        }
    }
}
