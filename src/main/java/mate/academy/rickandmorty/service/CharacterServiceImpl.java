package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterMapper characterMapper
    ) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        if (count == 0) {
            throw new RuntimeException("No characters found in database");
        }
        long randomId = random.nextLong(count) + 1;
        return characterRepository.findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Character not found with id: "
                        + randomId));
    }

    public List<CharacterDto> searchByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}

