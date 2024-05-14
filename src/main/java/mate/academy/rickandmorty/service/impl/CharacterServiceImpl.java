package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final Random random;

    @Override
    public CharacterDto getRandomCharacter() {
        long id = random.nextLong(repository.count());

        return repository.findById(id)
                 .map(mapper::toDto)
                 .orElseThrow(() -> new NoSuchElementException("Failed to get a random character"));
    }

    @Override
    public List<CharacterDto> getAllCharactersByName(String name, Pageable pageable) {
        return repository.findAllByNameContainingIgnoreCase(name).stream()
                 .map(mapper::toDto)
                 .toList();
    }
}
