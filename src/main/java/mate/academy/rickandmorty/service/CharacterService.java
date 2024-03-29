package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterService {

    private static final Random RANDOM_GENERATOR = new Random();

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CartoonCharacterDto getRandomCharacter() {
        long entriesCount = characterRepository.count() + 1L;
        long randomId = RANDOM_GENERATOR.nextLong(entriesCount);

        return characterRepository.findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Character doesn't exist with id: "
                        + randomId));
    }

    public List<CartoonCharacterDto> searchByName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
