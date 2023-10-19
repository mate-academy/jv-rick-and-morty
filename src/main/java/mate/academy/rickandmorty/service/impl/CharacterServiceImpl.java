package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final long minId = 1L;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandom() {
        long numberOfCharacters = characterRepository.count();
        Random random = new Random();
        long randomId = random.nextLong(numberOfCharacters + minId);
        return characterRepository.findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @Override
    public List<CharacterResponseDto> search(String name) {
        return characterRepository.findCharacterByNameContaining(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public List<Character> saveAll(List<ExternalCharacterDto> externalCharacterDtos) {
        List<Character> characters = externalCharacterDtos.stream()
                .map(characterMapper::toModel)
                .toList();
        return characterRepository.saveAll(characters);
    }
}
