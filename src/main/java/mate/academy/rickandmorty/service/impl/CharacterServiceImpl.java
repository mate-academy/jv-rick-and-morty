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
        long count = characterRepository.count();
        int randomIndex = new Random().nextInt((int) count);
        return characterMapper.toDto(characterRepository.findById((long) randomIndex)
                .orElseThrow(() -> new RuntimeException("Can't find random character")));
    }


    @Override
    public List<CharacterDto> getCharacterByName(String argument, Pageable pageable) {
        return characterRepository.findByNameContainingIgnoreCase(argument, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
