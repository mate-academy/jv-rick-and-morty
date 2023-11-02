package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void saveAllCharacters() {
        List<Character> list =
                client.getCharacters()
                        .stream()
                        .map(characterMapper::toModel)
                        .toList();
        characterRepository.saveAll(list);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long numberOfCharacters = characterRepository.count();
        long randomId = RANDOM.nextLong(numberOfCharacters + 1L);
        return characterRepository.findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Character was not found by id "
                        + randomId));
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
