package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private static final long MIN_CHARACTER_ID = 1;
    private static final long UPPER_BOUND = 1;
    private final Random randomCharacterGenerator = new Random();
    private final RickAndMortyRepository rickAndMortyRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long maxGeneratedNumber = rickAndMortyRepository.count() + UPPER_BOUND;
        return characterMapper.toDto(rickAndMortyRepository.findById(
                getRandomCharacterId(maxGeneratedNumber)).orElseThrow(() ->
                new NoSuchElementException("Can't find Character in DB")));
    }

    @Override
    public List<CharacterResponseDto> findByNameLike(String params) {
        List<Character> foundedCharactersFromDB =
                rickAndMortyRepository.findAllByNameContainsIgnoreCase(params);
        return foundedCharactersFromDB.stream()
                .map(characterMapper::toDto)
                .toList();
    }

    private long getRandomCharacterId(long maxGeneratedNumber) {
        return randomCharacterGenerator.nextLong(MIN_CHARACTER_ID, maxGeneratedNumber);
    }
}
