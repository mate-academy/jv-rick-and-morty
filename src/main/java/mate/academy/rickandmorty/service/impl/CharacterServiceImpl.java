package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final Random random;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = random.nextLong(count + 1);
        Character character = characterRepository.findById(randomId)
                .orElseThrow(RuntimeException::new);
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findByNamePart(String namePart) {
        return characterRepository.findAllByNameContainingIgnoreCase(namePart).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
