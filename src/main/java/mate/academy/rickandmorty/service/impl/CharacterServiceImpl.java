package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final int MAX_CHARACTERS_LIMIT = 827;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public CharacterDto getRandom() {
        Long randomId = (long) new Random().nextInt(MAX_CHARACTERS_LIMIT);
        return characterMapper.toDto(characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by id: " + randomId)
        ));
    }

    @Override
    public List<CharacterDto> getAllByNameContains(String name, Pageable pageable) {
        return characterRepository.findAllByNameContainsIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
