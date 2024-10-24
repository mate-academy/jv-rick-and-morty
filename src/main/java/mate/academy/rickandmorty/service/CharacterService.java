package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private static final int EMPTY_TABLE = 0;

    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        if (characterRepository.count() == EMPTY_TABLE) {
            List<Character> allCharacters
                    = rickAndMortyClient.getAllCharactersFromApi()
                    .stream()
                    .map(characterMapper::toModel)
                    .toList();
            characterRepository.saveAll(allCharacters);
        }
    }

    public CharacterDto getRandomCharacter() {
        Long randomId = new Random().nextLong(characterRepository.count());
        return characterRepository
                .findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow(()
                        -> new EntityNotFoundException("Can t find character by id: " + randomId));
    }

    public List<CharacterDto> searchCharactersByName(String name) {
        return characterMapper.toDto(
                characterRepository.findAllByNameIsContainingIgnoreCase(name)
        );
    }
}
