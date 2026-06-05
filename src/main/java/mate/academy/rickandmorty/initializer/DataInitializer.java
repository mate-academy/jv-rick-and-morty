package mate.academy.rickandmorty.initializer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public void run(String... args) {
        if (characterRepository.count() > 0) {
            return;
        }

        String url = "https://rickandmortyapi.com/api/character";

        while (url != null) {
            ApiResponseDto apiResponseDto = characterClient.fetchCharacters(url);

            if (apiResponseDto == null || apiResponseDto.getResults() == null) {
                break;
            }

            List<Character> characters = apiResponseDto.getResults()
                    .stream()
                    .map(mapper::toModel)
                    .toList();

            characterRepository.saveAll(characters);

            url = apiResponseDto.getInfo().getNext();
        }
    }
}
