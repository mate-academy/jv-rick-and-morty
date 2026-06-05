package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper mapper;
    private final CharacterRepository characterRepository;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterRepository.count();

        Random random = new Random();
        long randomId = random.nextLong(count) + 1;

        Character character = characterRepository.findById(randomId)
                .orElseThrow(() -> new RuntimeException("CharacterNotFound"));

        return mapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findAllByName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
