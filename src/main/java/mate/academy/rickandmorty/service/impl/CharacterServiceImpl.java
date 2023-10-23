package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private Random random = new Random();

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long characterSize = characterRepository.count();
        Long randomId = random.nextLong(characterSize);
        Character character = characterRepository
                .findById(randomId).orElseThrow(
                        () -> new EntityNotFoundException("Can't find random character"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findCharacterByInputValue(String value) {
        return characterRepository
                .findCharacterByNameContaining(value)
                .stream().map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
