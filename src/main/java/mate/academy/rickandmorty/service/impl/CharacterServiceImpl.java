package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;

    @Transactional
    @Override
    public InternalCharacterDto getRandomCharacter() {
        Long randomId = random.nextLong(characterRepository.count()) + 1;
        return characterMapper.toDto(characterRepository.findById(randomId)
                .orElseThrow(()
                        -> new CharacterNotFoundException("Can`t find a character by id: "
                        + randomId)));
    }

    @Override
    public List<InternalCharacterDto> getCharactersByName(String partialName) {
        return characterRepository.findAllByNameContaining(partialName)
                .stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
