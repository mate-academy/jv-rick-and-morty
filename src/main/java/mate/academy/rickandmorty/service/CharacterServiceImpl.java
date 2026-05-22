package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService, CommandLineRunner {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Value("${rickandmorty.api.base-url}")
    private String baseUrl;

    @Override
    public List<InternalCharacterDto> findAllByNameContainingIgnoreCase(String name) {
        List<Character> characters = characterRepository
                .findAllByNameContainingIgnoreCase(name);
        return characters.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public InternalCharacterDto getRandomCharacter() {
        return mapper.toDto(characterRepository.findRandom());
    }

    @Override
    public void run(String... args) throws Exception {
        syncExternalCharacters();
    }

    @Override
    public void syncExternalCharacters() {
        String url = baseUrl;
        while (url != null) {
            RickAndMortyResponseDto responseDto = client.fetchDataFromApi(url);
            List<Character> charactersToSave = responseDto.results().stream()
                    .map(mapper::toModel)
                    .toList();
            characterRepository.saveAll(charactersToSave);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted", e);
            }
            url = responseDto.info().next();
        }

    }

}
