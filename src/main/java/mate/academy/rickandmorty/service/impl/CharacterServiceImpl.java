package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        return characterMapper.toDto(characterRepository.findById(
                random.nextLong(characterRepository.count()) + 1L).get());
    }

    @Override
    public List<CharacterDto> searchCharactersByName(String searchParam, Pageable pageable) {
        return characterRepository.findByNameContainsAllIgnoreCase(searchParam, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
