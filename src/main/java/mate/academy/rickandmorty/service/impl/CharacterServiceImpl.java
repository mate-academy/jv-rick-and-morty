package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final int FIRST_INDEX = 1;
    private static long CHARACTERS_AVAILABLE;

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandom() {
        Long randomId = generateRandomId();
        return characterMapper.toResponseDto(characterRepository.findById(randomId).orElseThrow(
                () -> new NoSuchElementException("Can't find random character with id " + randomId)
        ));
    }

    @Override
    public List<CharacterResponseDto> findAllByNamePart(String namePart) {
        return characterRepository.findAllByNameContains(namePart).stream()
                .map(characterMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private Long generateRandomId() {
        if (CHARACTERS_AVAILABLE == 0) {
            CHARACTERS_AVAILABLE = characterRepository.count();
        }
        return ThreadLocalRandom.current().nextLong(FIRST_INDEX, CHARACTERS_AVAILABLE + 1);
    }
}
