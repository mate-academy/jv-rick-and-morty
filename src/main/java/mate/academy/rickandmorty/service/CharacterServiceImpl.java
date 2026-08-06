package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Long> ids = characterRepository.findAllIds();
        if (ids.isEmpty()) {
            throw new RuntimeException("No characters available in the database.");
        }
        Long randomId = ids.get(random.nextInt(ids.size()));
        Character character = characterRepository.findById(randomId)
                .orElseThrow(() -> new RuntimeException("Character not found with id: "
                        + randomId));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> searchByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}


