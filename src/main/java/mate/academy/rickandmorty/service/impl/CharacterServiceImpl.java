package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Long id = RANDOM.nextLong(characterRepository.count());
        return characterMapper.toDto(characterRepository.getReferenceById(id));
    }

    @Override
    public List<CharacterDto> getCharacterByNameContaining(String name) {
        return characterRepository.findCharacterByNameContaining(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
