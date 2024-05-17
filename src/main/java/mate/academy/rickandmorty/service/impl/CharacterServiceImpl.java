package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.exception.NoSuchCharacterException;
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
    public CharacterResponseDto getRandomCharacter() {
        long randomId = random.nextLong(1, repository.count());
        return mapper.toDto(repository.findById(randomId).orElseThrow(
                () -> new NoSuchCharacterException("Can't find any Character by id: " + randomId)
        ));
    }

    @Override
    public List<CharacterResponseDto> findAllByName(Pageable pageable, String name) {
        return mapper.toDto(repository.findAllByNameContainsIgnoreCase(pageable, name));
    }
}
